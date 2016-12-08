package major.mutation.plugin.coverage;

import major.mutation.Config;
import org.junit.Test;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.*;
import org.junit.runner.*;
import java.util.*;

public class CoverageTestRunner {

  private List<Integer> killedMutants;
  private HashMap<String, List<Integer>> coverage;
  private TestClass testClass;

  public CoverageTestRunner(HashMap coverage, TestClass testClass) {
    this.coverage = coverage;
    this.testClass = testClass;
    killedMutants = new ArrayList<Integer>();
  }

  // TODO: Optimize this or instead of calculating, just cache from CoverageListener
 	public List<Integer> getMutants(){
		List<Integer> mutants = new ArrayList<Integer>();
		for(List<Integer> m : coverage.values()){
			for(int i = 0; i < m.size(); i++){
				if(!mutants.contains(m.get(i))){
					mutants.add(m.get(i));
				}
			}
		}
		return mutants;
	}

  public void run() {
    List<Integer> mutants = getMutants();
    Request request;
    Result result;
    JUnitCore runner = new JUnitCore();
    for (int mutant : mutants) {
      Config.__M_NO = mutant;
      for (String testCase : coverage.keySet()) {
        if(!coverage.get(testCase).contains(mutant)) {
          continue;
        }
        request = Request.method(testClass.getJavaClass(), testCase);
        result = runner.run(request);
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
