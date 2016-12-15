package major.mutation.plugin.coverage;

import major.mutation.Config;
import major.mutation.plugin.TestSuiteRunner;
import major.mutation.plugin.util.*;
import org.junit.runners.model.TestClass;
import org.junit.runner.*;
import java.util.*;


public class CoverageRunner implements TestSuiteRunner{

  private int result;
  private TestClass[] testClasses;

  // load the Test Suite
  public void loadTest(TestClass[] testSuites) {
    testClasses = testSuites;
  }

  // run the Test Suite
  public void runTest() {
    CoverageInformation[] information = new CoverageInformation[testClasses.length];
    JUnitCore junit = new JUnitCore();
    for(int i = 0; i < testClasses.length; i++) {
      CoverageListener coverageListener = new CoverageListener();
      junit.addListener(coverageListener);
      junit.run(testClasses[i].getJavaClass());
      information[i] = coverageListener.getRunInformation();
      junit.removeListener(coverageListener);
    }


    CoverageTestRunner testRunner = new CoverageTestRunner(information, testClasses);
    testRunner.run();
    result = testRunner.getMutantKilled().size();
    /*System.out.println("Mutants killed:");
    for(int mut : mutantKilled) {
      System.out.print(mut + " ");
    }
    System.out.println("\nTotal Mutants killed: " + mutantKilled.size());*/
  }

  // return the TestSuite to be run
  public int getResult() {
    return result;
  }
}
