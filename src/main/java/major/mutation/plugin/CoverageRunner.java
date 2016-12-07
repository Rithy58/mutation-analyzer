package major.mutation.plugin;

import org.junit.runners.model.TestClass;
import org.junit.runner.Result;
import org.junit.runner.JUnitCore;


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
    CoverageListener listener = new CoverageListener();
    junit.addListener(listener);
    Result junitResult = junit.run(testClass.getJavaClass());
    result = junitResult.getRunCount();
  }

  // return the TestSuite to be run
  public int getResult() {
    return result;
  }
}
