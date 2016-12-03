package major.mutation.plugin;

import org.junit.runners.model.TestClass;

public class singleTestLoader implements TestSuiteBuilder {

  private String className;
  private TestClass testSuite;

  public singleTestLoader(String name) {
    className = name;
  }

  public void setClassName(String name) {
    className = name;
  }

  // load the Test Suite and return true if successful
  public boolean loadTest() {
    // TODO: Load the test, return false if test not found
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
