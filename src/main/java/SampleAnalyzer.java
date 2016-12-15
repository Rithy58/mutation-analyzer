import major.mutation.Analyzer;
import major.mutation.plugin.naive.NaiveRunner;
import major.mutation.plugin.coverage.CoverageRunner;
import major.mutation.plugin.*;

public class SampleAnalyzer {
  public static void main(String[] args) {
        System.out.println("Initializing Major Mutation Analyzer...");
        Analyzer analyzer = new Analyzer();

        System.out.println("Loading plugins...");
        // load plugins
        // TestSuiteBuilder testLoader = new SingleTestLoader("TestSuite");
        TestSuiteBuilder testLoader = new MultiTestLoader("TestSuite", "TestSuite0");

        TestSuiteRunner naiveRunner = new NaiveRunner();
        TestSuiteRunner coverageRunner = new CoverageRunner();

        ScoreCalculator testResult = new MutantKilled();

        // Register plugins
        analyzer.registerTestSuiteBuilder(testLoader);
        analyzer.registerScoreCalculator(testResult);


        System.out.println("Running Analyzer with naive...");
        analyzer.registerTestSuiteRunner(naiveRunner);
        long startTime = System.nanoTime();
        analyzer.run();
        long duration = System.nanoTime() - startTime;
        System.out.println("Naive finished in " + duration + " nanoseconds");

        System.out.println("Running Analyzer with coverage...");
        analyzer.registerTestSuiteRunner(coverageRunner);
        startTime = System.nanoTime();
        analyzer.run();
        duration = System.nanoTime() - startTime;
        System.out.println("Coverage finished in " + duration + " nanoseconds");
  }
}
