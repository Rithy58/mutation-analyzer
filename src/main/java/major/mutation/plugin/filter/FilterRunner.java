package major.mutation.plugin.filter;

import major.mutation.Config;
import major.mutation.plugin.TestSuiteRunner;
import major.mutation.plugin.coverage.CoverageListener;
import major.mutation.plugin.util.*;
import org.junit.runners.model.TestClass;
import org.junit.runner.*;
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
    for(int i = 0; i < testClasses.length; i++) {
      CoverageListener coverageListener = new CoverageListener();
      junit.addListener(coverageListener);
      junit.run(testClasses[i].getJavaClass());
      information[i] = coverageListener.getRunInformation();
      junit.removeListener(coverageListener);
    }


  }

  public int getResult() {
    return result;
  }
}
