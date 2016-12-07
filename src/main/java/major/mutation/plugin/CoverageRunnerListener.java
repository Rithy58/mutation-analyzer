package major.mutation.plugin;

import org.junit.runner.notification.RunListener;
import org.junit.runner.Description;
import major.mutation.Config;
import java.util.*;

public class CoverageRunnerListener extends RunListener {

	HashMap<String, List<Integer>> coverage = new HashMap<String, List<Integer>>();

	public void addCoverage(HashMap c) {
		coverage = c;
	}

	public void testRunStarted (Description description) {
		Config.__M_NO = 0;
	}

	public void testStarted (Description description) {
		Config.reset();
	}

	public void testFinished (Description description) {
		List<Integer> covered = new ArrayList<Integer>();
		covered = Config.getCoverageList();
		coverage.put(description.getMethodName(), covered);

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
