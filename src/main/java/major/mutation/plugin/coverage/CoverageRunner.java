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
    TimeoutRunner[] timedRunners = new TimeoutRunner[testClasses.length];
    JUnitCore junit = new JUnitCore();
    for(int i = 0; i < testClasses.length; i++) {
      try {
        timedRunners[i] = new TimeoutRunner(testClasses[i].getJavaClass());
      } catch (InitializationError error) {
        continue;
      }
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
        if (killMutant(testClasses[t], information[t], timedRunners[t])) {
          killedMutants.add(Config.__M_NO);
          result ++;
          break;
        }
      }
    }
  }

  private boolean killMutant(TestClass testClass, CoverageInformation information, TimeoutRunner runner) {
    for (FrameworkMethod method : runner.getChildren()) {
      String testCase = method.getName();
      if (!information.get(testCase).contains(Config.__M_NO)) continue;
      if (runner.runTimeoutChild(method)) {
        return true;
      }
    }
    return false;
    // Request request;
    // Result result;
    // List<String> methods = information.getKeys();
    // for (String testCase : methods) {
    //   if(!information.get(testCase).contains(Config.__M_NO)) {
    //     continue;
    //   }
    //   request = Request.method(testClass.getJavaClass(), testCase);
    //   result = runner.run(request);
    //   if (result.getFailureCount() > 0) {
    //     return true;
    //   }
    // }
    // return false;
  }

  // return the TestSuite to be run
  public int getResult() {
    return result;
  }
}
