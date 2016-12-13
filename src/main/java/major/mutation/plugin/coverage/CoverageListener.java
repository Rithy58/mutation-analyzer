package major.mutation.plugin.coverage;

import major.mutation.Config;
import major.mutation.plugin.util.*;
import org.junit.runner.notification.RunListener;
import org.junit.runner.Description;
import java.util.*;

public class CoverageListener extends RunListener {

	private CoverageInformation runInformation;

	public CoverageListener() {
		runInformation = new CoverageInformation();
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

	private long startTime;

	public void testStarted (Description description) {
		Config.reset();
		startTime = System.nanoTime();
	}

	public void testFinished (Description description) {
		runInformation.put(description.getMethodName(), Config.getCoverageList(), System.nanoTime() - startTime);
	}

	public CoverageInformation getRunInformation() {
		return runInformation;
	}
}
