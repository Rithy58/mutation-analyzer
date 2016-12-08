package major.mutation.plugin.coverage;

import major.mutation.Config;
import org.junit.runner.notification.RunListener;
import org.junit.runner.Description;
import java.util.*;

public class CoverageListener extends RunListener {

	private HashMap<String, List<Integer>> coverage;

	public CoverageListener() {
		coverage = new LinkedHashMap<String, List<Integer>>();
		Config.__M_NO = 0;
	}

	public void testRunStarted (Description description) {
		//List<Integer> covered = new ArrayList<Integer>();
		//covered = Config.getCoverageList();

		//TODO : experiement with printing out the mutants

		//coverage.put(description.getMethodName(), covered);
		// Get coverage before any test has been run
		//coverage.put("Init", covered);
	}

	public void testStarted (Description description) {
		Config.reset();
	}

	public void testFinished (Description description) {
		coverage.put(description.getMethodName(), Config.getCoverageList());
	}

	public HashMap<String, List<Integer>> getCoverage(){
		return coverage;
	}
}
