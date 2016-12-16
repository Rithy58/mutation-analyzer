package major.mutation.plugin;

import org.junit.runners.model.TestClass;
import major.mutation.plugin.util.*;
import junit.runner.Version;
public class MultiTestLoader implements TestSuiteBuilder {

  private String[] className;
  private TestClass[] testSuite;

  public MultiTestLoader(String... name) {
    className = name;
    testSuite = new TestClass[name.length];System.out.println("JUnit version is: " + Version.id());
  }

  // load the Test Suite and return true if successful
  public boolean loadTest() {
    // TODO: Load the test, return false if test not found
    for (int i = 0; i < className.length; i ++) {
      try {
        testSuite[i] = new TestClass(Class.forName(className[i]));
      } catch (Throwable throwable) {
        throwable.printStackTrace();
        return false;
      }
    }
    return true;
  }

  // set up the Test Suite
  public void setupTest() {
    //probably doesn't need to setup the test for this
  }

  // return the TestSuite to be run
  public TestClass[] getTestSuite() {
    return testSuite;
  }
}
