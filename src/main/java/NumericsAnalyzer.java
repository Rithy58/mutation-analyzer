import major.mutation.Analyzer;
import major.mutation.plugin.coverage.CoverageRunner;
import major.mutation.plugin.*;

public class NumericsAnalyzer {
  public static void main(String[] args) {
        System.out.println("Initializing Major Mutation Analyzer...");
        Analyzer analyzer = new Analyzer();

        System.out.println("Loading plugins...");
        // TODO: load plugins
        // TestSuiteBuilder testLoader = new SingleTestLoader("TestSuite");
        TestSuiteBuilder testLoader = new MultiTestLoader("TestSuite", "TestSuite0");
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
