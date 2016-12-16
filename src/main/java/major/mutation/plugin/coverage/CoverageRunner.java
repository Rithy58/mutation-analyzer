package major.mutation.plugin.coverage;

import major.mutation.Config;
import major.mutation.plugin.TestSuiteRunner;
import major.mutation.plugin.util.*;
import org.junit.runners.model.TestClass;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runner.*;
import java.util.*;

public class CoverageRunner implements TestSuiteRunner {

  private static final long JOIN_TIMEOUT = 1000;

  private int result;
  private TestClass[] testClasses;
  private List<Integer> killedMutants;

  // load the Test Suite
  public void loadTest(TestClass[] testSuites) {
    testClasses = testSuites;
    killedMutants = new ArrayList<Integer>();
    result = 0;
  }

  // run the Test Suite
  public void runTest() {
    CoverageInformation[] information = new CoverageInformation[testClasses.length];
    JUnitCore junit = new JUnitCore();
    for(int i = 0; i < testClasses.length; i++) {
      CoverageListener coverageListener = new CoverageListener();
      junit.addListener(coverageListener);
      junit.run(testClasses[i].getJavaClass());
      information[i] = coverageListener.getRunInformation();
      junit.removeListener(coverageListener);
    }

    Iterator<Integer> i = CoverageInformation.getAllMutants().iterator();
    while (i.hasNext()) {
      Config.__M_NO = i.next();
      for (int t = 0; t < testClasses.length; t ++) {
        if (!information[t].getValues().contains(Config.__M_NO)) {
          continue;
        }
        if (killMutant(testClasses[t], information[t], junit)) {
          System.out.println("Killed Mutant #" + Config.__M_NO);
          killedMutants.add(Config.__M_NO);
          result ++;
          break;
        }
      }
    }
  }

  private boolean killMutant(TestClass testClass, CoverageInformation information, JUnitCore runner) {
    Request request;
    Result result;
    MethodThread t;
    List<String> methods = information.getSortedKeys();
    for (String testCase : methods) {
      if(!information.get(testCase).contains(Config.__M_NO)) {
        continue;
      }
      t = new MethodThread(runner, testClass, testCase);
      t.start();
      try {
        // System.out.println(information.getRuntime(testCase));
        int milliseconds = 0;
        int deltaRemainder = information.getRuntime(testCase) * 100;
        if ( deltaRemainder > 999999 ) {
             milliseconds = deltaRemainder / 1000000;
             deltaRemainder = deltaRemainder % 1000000;
        }
        t.join(milliseconds, deltaRemainder);
      } catch (Exception e) {
        // Failed to interrupt
        t.interrupt();
      }
      if (!t.finished || !t.passed) {
        return true;
      }
    }
    return false;
  }

  // return the TestSuite to be run
  public int getResult() {
    return result;
  }

  private static class MethodThread extends Thread {
    private final JUnitCore junit;
    private final Class<?> testClass;
    private final String testMethod;
    private boolean passed = true;
    private boolean finished = false;

    public MethodThread(JUnitCore core, TestClass clazz, String method) {
      junit = core;
      testClass = clazz.getJavaClass();
      testMethod = method;
    }


    @Override
    public void run() {
      if (junit.run(Request.method(testClass, testMethod)).getFailureCount() > 0) {
        passed = false;
      }
      finished = true;
    }
  }

}
