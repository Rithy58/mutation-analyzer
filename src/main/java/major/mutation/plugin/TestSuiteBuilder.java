package major.mutation.plugin;

import org.junit.runners.model.TestClass;

public interface TestSuiteBuilder {
  // load the Test Suite and return true if successful
  public boolean loadTest();

  // set up the Test Suite
  public void setupTest();

  // return the TestSuite to be run
  public TestClass[] getTestSuite();
}
