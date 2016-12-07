package major.mutation.plugin;

import org.junit.runners.model.TestClass;
import org.junit.runner.Result;
import org.junit.runner.JUnitCore;
import java.util.*;


public class CoverageRunner implements TestSuiteRunner{
  // load the Test Suite
  private int result;
  private TestClass testClass;

  public void loadTest(TestClass testSuite) {
    testClass = testSuite;
  }

  // run the Test Suite
  public void runTest() {
    JUnitCore junit = new JUnitCore();
    CoverageListener coverageListener = new CoverageListener();
    junit.addListener(coverageListener);
    junit.run(testClass.getJavaClass());

    CoverageRunnerListener CRL = new CoverageRunnerListener();
    CRL.addCoverage(coverageListener.getCoverage());

    junit = new JUnitCore();
    junit.addListener(CRL);
    junit.run(testClass.getJavaClass());
  }

  // return the TestSuite to be run
  public int getResult() {
    return result;
  }
}
