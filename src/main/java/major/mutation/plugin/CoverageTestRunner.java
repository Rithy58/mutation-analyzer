package major.mutation.plugin;

import org.junit.Test;
import org.junit.runners.model.InitializationError;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.TestClass;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;

import major.mutation.Config;

import java.util.*;

public class CoverageTestRunner {

  private JUnitCore core;
  private List<Integer> killedMutants;
  private HashMap<String, List<Integer>> coverage;
  private HashMap<String, Long> runtime;
  private TestClass testClass;

  private CoverageTestRunner() {

  }

  public CoverageTestRunner(HashMap runtime, HashMap coverage, TestClass testClass) {
    core = new JUnitCore();
    this.runtime = runtime;
    this.coverage = coverage;
    this.testClass = testClass;
    killedMutants = new ArrayList<Integer>();
  }

 	public List<Integer> getMutants() {
		List<Integer> mutants = new ArrayList<Integer>();
		for(List<Integer> m : coverage.values()) {
			for(int i = 0; i < m.size(); i++) {
				if(!mutants.contains(m.get(i))) {
					mutants.add(m.get(i));
				}
			}
		}
		return mutants;
	}

  public void run() {
    List<String> methodList = new ArrayList<String>(coverage.keySet());
    methodList.sort(new Comparator<String>() {
      @Override
      public int compare(String s1, String s2) {
        // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
        long run1 = runtime.get(s1);
        long run2 = runtime.get(s2);
        if (run1 == run2) return 0;
        return run1 < run2 ? -1 : 1;
      }
    });
    for (int mutant : getMutants()) {
      Config.__M_NO = mutant;
      for (String testCase : methodList) {
        if(!coverage.get(testCase).contains(mutant)) {
          continue;
        }
        Request request = Request.method(testClass.getJavaClass(), testCase);
        Result result = core.run(request);
        if (result.getFailureCount() > 0) {
          killedMutants.add(mutant);
          break;
        }
      }
    }
  }

  public List<Integer> getMutantKilled() {
    return killedMutants;
  }

}
