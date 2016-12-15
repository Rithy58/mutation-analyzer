package major.mutation;
import major.mutation.plugin.*;

public class Analyzer {
  private TestSuiteBuilder tsBuilder;
  private TestSuiteRunner tsRunner;
  private ScoreCalculator tsCalculator;

  // default constructor with default plugins?
  public Analyzer() {
    // TODO: default plugins
  }

  // constructor that take in plugins?
  public Analyzer(TestSuiteBuilder builder, TestSuiteRunner runner, ScoreCalculator calculator) {
    tsBuilder = builder;
    tsRunner = runner;
    tsCalculator = calculator;
  }

  public void registerTestSuiteBuilder(TestSuiteBuilder builder) {
    tsBuilder = builder;
  }

  public void registerTestSuiteRunner(TestSuiteRunner runner) {
    tsRunner = runner;
  }

  public void registerScoreCalculator(ScoreCalculator calculator) {
    tsCalculator = calculator;
  }

  // TODO: Maybe clean this up, more concise
  public void run() {
    if (tsBuilder.loadTest()) {
      tsBuilder.setupTest();
      tsRunner.loadTest(tsBuilder.getTestSuite());
      tsRunner.runTest();
      tsCalculator.loadResult(tsRunner.getResult());
      tsCalculator.calculate();
      tsCalculator.outputResult();
    } else {
      // TODO: Throw exception
    }
  }
}
