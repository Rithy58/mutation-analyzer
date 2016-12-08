package major.mutation.plugin;

import org.junit.Test;
import org.junit.runners.model.InitializationError;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.TestClass;
import org.junit.runners.model.FrameworkMethod;
import java.util.*;

public class CoverageTestRunner {

  private HashMap<String, List<Integer>> coverage;
  private TestClass testSuite;

  public CoverageTestRunner(HashMap coverage, TestClass testClass) {
    this.coverage = coverage;
    testSuite = testClass;
  }

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



}
