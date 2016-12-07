import major.mutation.Analyzer;
import major.mutation.plugin.*;

public class SampleAnalyzer {
  public static void main(String[] args) {
        System.out.println("Initializing Major Mutation Analyzer...");
        Analyzer analyzer = new Analyzer();

        System.out.println("Loading plugins...");
        // TODO: load plugins
        TestSuiteBuilder testLoader = new SingleTestLoader("TestSuite");
        TestSuiteRunner testRunner = new CoverageRunner();
        ScoreCalculator testResult = new MutantKilled();

        // TODO: Register plugins
        analyzer.registerTestSuiteBuilder(testLoader);
        analyzer.registerTestSuiteRunner(testRunner);
        analyzer.registerScoreCalculator(testResult);

        System.out.println("Running Analyzer...");
        analyzer.run();
  }
}
