package major.mutation.plugin.filter;

import major.mutation.Config;
import major.mutation.plugin.TestSuiteRunner;
import major.mutation.plugin.coverage.CoverageListener;
import major.mutation.plugin.util.*;
import org.junit.runners.model.TestClass;
import org.junit.runner.*;
import org.junit.runner.manipulation.Filter;
import java.util.*;

public class FilterRunner implements TestSuiteRunner {
  private int result;
  private TestClass[] testClasses;

  // load the Test Suite
  public void loadTest(TestClass[] testSuites) {
    testClasses = testSuites;
    result = 0;
  }

  public void runTest() {
    CoverageInformation[] information = new CoverageInformation[testClasses.length];
    JUnitCore junit = new JUnitCore();
    Class tests[] = new Class[testClasses.length];
    for(int i = 0; i < testClasses.length; i++) {
      tests[i] = testClasses[i].getJavaClass();
    }
    for(int i = 0; i < tests.length; i++) {
      CoverageListener coverageListener = new CoverageListener();
      junit.addListener(coverageListener);
      junit.run(tests[i]);
      information[i] = coverageListener.getRunInformation();
      junit.removeListener(coverageListener);
    }


  }

  public int getResult() {
    return result;
  }

  private void createFilter(CoverageInformation information, Class tests[], int mutant) {
    //Description desc = new Description();
    for(int i = 0; i < tests.length; i++) {

    }
  }
}
