package major.mutation.plugin.coverage;

import major.mutation.Config;
import major.mutation.plugin.TestSuiteRunner;
import org.junit.runners.model.TestClass;
import org.junit.runner.*;
import java.util.*;

public class CoverageRunner implements TestSuiteRunner{

  private int result;
  private TestClass testClass;

  // load the Test Suite
  public void loadTest(TestClass testSuite) {
    testClass = testSuite;
  }

  // run the Test Suite
  public void runTest() {
    JUnitCore junit = new JUnitCore();
    CoverageListener coverageListener = new CoverageListener();
    junit.addListener(coverageListener);
    junit.run(testClass.getJavaClass());

    CoverageTestRunner testRunner = new CoverageTestRunner(coverageListener.getCoverage(), testClass);
    testRunner.run();
    List<Integer> mutantKilled = testRunner.getMutantKilled();
    System.out.println("Mutants killed:");
    for(int mut : mutantKilled) {
      System.out.print(mut + " ");
    }
    System.out.println("\nTotal Mutants killed: " + mutantKilled.size());
  }

  // return the TestSuite to be run
  public int getResult() {
    return result;
  }
}
