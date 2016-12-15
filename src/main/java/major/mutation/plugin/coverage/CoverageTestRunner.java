package major.mutation.plugin.coverage;

import major.mutation.Config;
import major.mutation.plugin.util.*;
import org.junit.Test;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.*;
import org.junit.runner.*;
import java.util.*;
import java.util.concurrent.*;

public class CoverageTestRunner {

  public static final long TIMEOUT_TIME = 1000;

  private List<Integer> killedMutants;
  private CoverageInformation[] information;
  private TestClass[] testClasses;

  public CoverageTestRunner(CoverageInformation[] information, TestClass[] testClass) {
    this.information = information;
    this.testClasses = testClass;
    killedMutants = new ArrayList<Integer>();
  }

  public void run() {
    JUnitCore runner = new JUnitCore();
    Iterator<Integer> i = CoverageInformation.getAllMutants().iterator();
    while (i.hasNext()) {
      Config.__M_NO = i.next();
      for (int t = 0; t < testClasses.length; t ++) {
        if (!information[t].getValues().contains(Config.__M_NO)) {
          continue;
        }
        if (killMutant(testClasses[t], information[t], runner)) {
          killedMutants.add(Config.__M_NO);
          System.out.println("Killed Mutant #" + Config.__M_NO);
          break;
        }
      }
    }
  }

  private boolean killMutant(TestClass testClass, CoverageInformation information, JUnitCore runner) {
    Request[] request = new Request[1];
    List<String> methods = information.getKeys();
    for (String testCase : methods) {
      if(!information.get(testCase).contains(Config.__M_NO)) {
        continue;
      }
      request[0] = Request.method(testClass.getJavaClass(), testCase);
      boolean[] runFinished = new boolean[] {false};
      boolean[] runResult =  new boolean[] {false};
      ExecutorService service = Executors.newSingleThreadExecutor();
      Future future = service.submit(new Runnable() {
        public void run() {
          Result result = runner.run(request[0]);
          runFinished[0] = true;
          if (result.getFailureCount() > 0) {
            runResult[0] = true;
          }
        }
      });
      try {service.awaitTermination(TIMEOUT_TIME, TimeUnit.MILLISECONDS);} catch (Exception e) {e.printStackTrace();}
      service.shutdown();

      if (!runFinished[0] || runResult[0]) {
        return true;
      }
    }
    return false;
  }

  public List<Integer> getMutantKilled() {
    return killedMutants;
  }
}
