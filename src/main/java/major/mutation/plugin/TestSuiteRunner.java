package major.mutation.plugin;

import org.junit.runners.model.TestClass;
import org.junit.runner.Result;


public interface TestSuiteRunner {
  // load the Test Suite
  public void loadTest(TestClass testSuite);

  // run the Test Suite
  public void runTest();

  // return the TestSuite to be run
  public Result getResult();
}
