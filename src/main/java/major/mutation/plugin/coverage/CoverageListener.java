package major.mutation.plugin.coverage;

import major.mutation.Config;
import org.junit.runner.notification.RunListener;
import org.junit.runner.Description;
import java.util.*;

public class CoverageListener extends RunListener {

	private HashMap<String, List<Integer>> coverage;

	public CoverageListener() {
		coverage = new LinkedHashMap<String, List<Integer>>();
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
	}

	public HashMap<String, List<Integer>> getCoverage(){
		return coverage;
	}
}
