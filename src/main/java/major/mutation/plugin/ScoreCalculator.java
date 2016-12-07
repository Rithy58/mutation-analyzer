package major.mutation.plugin;

import org.junit.runners.model.TestClass;
import org.junit.runner.Result;


public interface ScoreCalculator {
  // load the result
  public void loadResult(int result);

  // calculate the score
  public void calculate();

  // output the result, maybe override the toString()?
  public void outputResult();
}
