package major.mutation.plugin.naive;

import major.mutation.Config;
import major.mutation.plugin.TestSuiteRunner;
import org.junit.runners.model.TestClass;
import org.junit.runner.*;
import java.util.*;

public class NaiveRunner implements TestSuiteRunner {
  private int result;
  private TestClass[] testClasses;

  // load the Test Suite
  public void loadTest(TestClass[] testSuites) {
    testClasses = testSuites;
    result = 0;
  }

  public void runTest() {
    JUnitCore junit = new JUnitCore();
    Config.__M_NO = 0;
    Class tests[] = new Class[testClasses.length];
    for(int i = 0; i < testClasses.length; i++) {
      tests[i] = testClasses[i].getJavaClass();
    }
    junit.run(tests);
    List<Integer> mutants = Config.getCoverageList();

    for(Integer mut : mutants){
        Config.__M_NO=mut;
        Result testResult = junit.run(tests);
        if(!testResult.wasSuccessful()) {
          result++;
        }
    }
  }

  public int getResult() {
    return result;
  }
}
