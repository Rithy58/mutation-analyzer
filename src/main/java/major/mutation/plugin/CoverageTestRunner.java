package major.mutation.plugin;

import org.junit.Test;
import org.junit.runners.model.InitializationError;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.TestClass;
import org.junit.runners.model.FrameworkMethod;
import java.util.*;

public class CoverageTestRunner extends BlockJUnit4ClassRunner {

  private HashMap<String, List<Integer>> coverage;

  public CoverageTestRunner(TestClass testClass, HashMap coverage) throws InitializationError {
    super(testClass.getJavaClass());
    this.coverage = coverage;
  }

  @Override
  protected List<FrameworkMethod> getChildren() {
    return computeTestMethods();
 	}

  /*
  Returns the methods that run tests. Default implementation returns all methods annotated
  with @Test on this class superclasses that are not overridden.
  */
  @Override
  protected List<FrameworkMethod> computeTestMethods() {
    // I think this would be where we would use the coverage information and further filter the list
    // TODO: Utilize coverage infomration to filter the list of test methods to be run
    return getTestClass().getAnnotatedMethods(Test.class);
  }

}
