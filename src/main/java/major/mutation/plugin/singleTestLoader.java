package major.mutation.plugin;

import org.junit.runners.model.TestClass;

public class singleTestLoader implements TestSuiteBuilder {
  // load the Test Suite and return true if successful
  public boolean loadTest() {

    return true;
  }

  // set up the Test Suite
  public void setupTest() {

  }

  // return the TestSuite to be run
  public TestClass getTestSuite() {

    return null;
  }
}
