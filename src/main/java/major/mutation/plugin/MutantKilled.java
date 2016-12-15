package major.mutation.plugin;

import org.junit.runners.model.TestClass;
import org.junit.runner.Result;


public class MutantKilled implements ScoreCalculator {
  private int mutantsKilled;
  // load the result
  public void loadResult(int result) {
    mutantsKilled = result;
  }

  // calculate the score
  public void calculate() {

  }

  // output the result, maybe override the toString()?
  public void outputResult() {
    System.out.println("Mutants killed: " + mutantsKilled);
  }
}
