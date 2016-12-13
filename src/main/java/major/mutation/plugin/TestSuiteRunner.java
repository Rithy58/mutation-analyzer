package major.mutation.plugin;

import org.junit.runners.model.TestClass;

public interface TestSuiteRunner {
  // load the Test Suite
  public void loadTest(TestClass[] testSuite);

  // run the Test Suite
  public void runTest();

  // return the TestSuite to be run
  public int getResult();
}
