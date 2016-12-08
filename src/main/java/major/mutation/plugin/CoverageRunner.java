package major.mutation.plugin;

import org.junit.Test;
import org.junit.runners.model.TestClass;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runner.Result;
import org.junit.runner.Request;
import org.junit.runner.JUnitCore;
import java.util.*;
import org.junit.runner.notification.RunListener;
import org.junit.runner.Description;

import major.mutation.Config;

import java.lang.reflect.Method;

public class CoverageRunner implements TestSuiteRunner{
  // load the Test Suite
  private int result;
  private TestClass testClass;

  public void loadTest(TestClass testSuite) {
    testClass = testSuite;
  }

  // run the Test Suite
  public void runTest() {
    try {
      CoverageRuntimeRunner suiteRunner = new CoverageRuntimeRunner(testClass);
      suiteRunner.run();
      // JUnitCore junit = new JUnitCore();
      // CoverageListener coverageListener = new CoverageListener();
      // HashMap<String, Long> runtimes = new HashMap<>();
      // junit.addListener(coverageListener);
      // Method[] testCases = testClass.getJavaClass().getDeclaredMethods();
      // for (Method method : testCases) {
      //   Request request = Request.method(testClass.getJavaClass(), method.getName());
      //   Result result = junit.run(request);
      //   runtimes.put(method.getName(), result.getRunTime());
      // }

      CoverageTestRunner testRunner = new CoverageTestRunner(suiteRunner.getRuntime(), suiteRunner.getCoverage(), testClass);
      testRunner.run();
      List<Integer> mutantKilled = testRunner.getMutantKilled();
      System.out.println("Mutants killed:");
      for(int mut : mutantKilled) {
        System.out.print(mut + " ");
      }
      System.out.println("\nTotal Mutants killed: " + mutantKilled.size());
    } catch (InitializationError e) {

    }
  }

  // return the TestSuite to be run
  public int getResult() {
    return result;
  }

  private class CoverageListener extends RunListener {

  	HashMap<String, List<Integer>> coverage = new LinkedHashMap<String, List<Integer>>();

  	public void testRunStarted (Description description) {
  		Config.__M_NO = 0;
  	}

  	public void testStarted (Description description) {
  		Config.reset();
  	}

  	public void testFinished (Description description) {
  		List<Integer> covered = new ArrayList<Integer>();
  		covered = Config.getCoverageList();
      String method = description.getMethodName();
  		coverage.put(method, covered);

  		/*System.out.print(description.getMethodName() + ":");
  		List<Integer> l = Config.getCoverageList();

  		for(Integer mut : l){
  			System.out.print(" " + mut);
  		}
  		System.out.println("\n");*/
  	}

  	public HashMap<String, List<Integer>> getCoverage(){
  		return coverage;
  	}

  }

}
