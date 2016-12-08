package major.mutation.plugin;

import org.junit.Test;
import org.junit.runners.model.TestClass;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runner.Result;
import org.junit.runner.Request;
import org.junit.runner.JUnitCore;
import java.util.*;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runner.Description;

import major.mutation.Config;

import java.lang.reflect.Method;

public class CoverageRuntimeRunner extends BlockJUnit4ClassRunner {

  private HashMap<String, List<Integer>> coverage;
  private HashMap<String, Long> runtime;

  public CoverageRuntimeRunner(TestClass testClass) throws InitializationError {
      super(testClass.getJavaClass());
      coverage = new HashMap<>();
      runtime = new HashMap<>();
  }

  @Override
  protected List<FrameworkMethod> computeTestMethods() {
    List<FrameworkMethod> methods = new ArrayList<>();
    for (Method m : getTestClass().getJavaClass().getDeclaredMethods()) {
      methods.add(new FrameworkMethod(m));
    }
    return methods;
  }

  public void run() {
    Config.__M_NO = 0;
    for (FrameworkMethod method : computeTestMethods()) {
      Config.reset();
      long startTime = System.nanoTime();
      try {
        methodBlock(method).evaluate();
      } catch (Throwable t) {

      } finally {
        long diffTime = System.nanoTime() - startTime;
        coverage.put(method.getName(), Config.getCoverageList());
        runtime.put(method.getName(), diffTime);
      }
    }
  }

  public HashMap<String, List<Integer>> getCoverage() {
    return coverage;
  }

  public HashMap<String, Long> getRuntime() {
    return runtime;
  }
}
