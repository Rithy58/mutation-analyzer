package major.mutation.plugin;

import org.junit.Test;
import org.junit.runners.model.InitializationError;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.TestClass;
import org.junit.runners.model.FrameworkMethod;
import java.util.*;

public class CoverageTestRunner {

  private HashMap<String, List<Integer>> coverage;

  public CoverageTestRunner(HashMap coverage, TestClass testClass) {
    this.coverage = coverage;
  }

}
