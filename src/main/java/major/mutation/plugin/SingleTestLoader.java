package major.mutation.plugin;

import org.junit.runners.model.TestClass;

public class SingleTestLoader implements TestSuiteBuilder {

  private String className;
  private TestClass testSuite;

  public SingleTestLoader(String name) {
    className = name;
  }

  // load the Test Suite and return true if successful
  public boolean loadTest() {
    // TODO: Load the test, return false if test not found
    try {
      Class testClass = Class.forName(className);
      testSuite = new TestClass(testClass);
    } catch (Throwable throwable) {
      return false;
    }
    return true;
  }

  // set up the Test Suite
  public void setupTest() {
    //probably doesn't need to setup the test for this
  }

  // return the TestSuite to be run
  public TestClass getTestSuite() {

    return testSuite;
  }
}
