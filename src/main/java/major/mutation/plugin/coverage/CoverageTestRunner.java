package major.mutation.plugin.coverage;

import major.mutation.Config;
import org.junit.Test;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.*;
import org.junit.runner.*;
import java.util.*;

public class CoverageTestRunner {

  private List<Integer> killedMutants;
  private TrackingLinkedHashMap<String, List<Integer>, Integer> information;
  private TestClass testClass;

  public CoverageTestRunner(TrackingLinkedHashMap<String, List<Integer>, Integer> information, TestClass testClass) {
    this.information = information;
    this.testClass = testClass;
    killedMutants = new ArrayList<Integer>();
  }

  public void run() {
    Request request;
    Result result;
    JUnitCore runner = new JUnitCore();
    int mut;
    List<String> methods = information.getKeys();
    Iterator<Integer> i = information.getValues().iterator();
    while (i.hasNext()) {
      mut = i.next();
      Config.__M_NO = mut;
      for (String testCase : methods) {
        if(!information.get(testCase).contains(mut)) {
          continue;
        }
        request = Request.method(testClass.getJavaClass(), testCase);
        result = runner.run(request);
        if (result.getFailureCount() > 0) {
          killedMutants.add(mut);
          break;
        }
      }
    }
  }

  public List<Integer> getMutantKilled() {
    return killedMutants;
  }
}
