package major.mutation.plugin.coverage;

import major.mutation.Config;
import major.mutation.plugin.TestSuiteRunner;
import major.mutation.plugin.util.*;
import org.junit.runners.model.TestClass;
import org.junit.runner.*;
import java.util.*;

public class CoverageOnlyRunner implements TestSuiteRunner {
  private int result;
  private TestClass[] testClasses;
  private List<Integer> killedMutants;

  // load the Test Suite
  public void loadTest(TestClass[] testSuites) {
    testClasses = testSuites;
    result = 0;
    killedMutants = new ArrayList<Integer>();
  }

  // run the Test Suite
  public void runTest() {
    JUnitCore junit = new JUnitCore();
    CoverageInformation[] information = new CoverageInformation[testClasses.length];
    CoverageInformation.reset();
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
          result ++;
          killedMutants.add(Config.__M_NO);
          break;
        }
      }
    }
  }

  private boolean killMutant(TestClass testClass, CoverageInformation information, JUnitCore runner) {
    Request request;
    Result result;
    List<String> methods = information.getKeys();
    for (String testCase : methods) {
      if(!information.get(testCase).contains(Config.__M_NO)) {
        continue;
      }
      request = Request.method(testClass.getJavaClass(), testCase);
      result = runner.run(request);
      if (result.getFailureCount() > 0) {
        return true;
      }
    }
    return false;
  }

  public int getResult() {
    return result;
  }
}
