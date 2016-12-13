package major.mutation;

import org.junit.Test;
import static org.junit.Assert.*;
import major.mutation.Config;
import major.mutation.Analyzer;
import major.mutation.plugin.*;
import major.mutation.plugin.coverage.*;

public class AnalyzerTest {
    @Test
    public void testAnalyzer() {
        Analyzer analyzer = new Analyzer();
        assertNotNull("Analyzer failed to instantiate", analyzer);
        TestSuiteBuilder testLoader = new MultiTestLoader("test", "test2");
        TestSuiteBuilder singleTestLoader = new SingleTestLoader("test");
        TestSuiteRunner testRunner = new CoverageRunner();
        ScoreCalculator testResult = new MutantKilled();

        analyzer.registerTestSuiteBuilder(testLoader);
        analyzer.registerTestSuiteRunner(testRunner);
        analyzer.registerScoreCalculator(testResult);

        analyzer.run();

        Analyzer analyzer2 = new Analyzer(testLoader, testRunner, testResult);
    }
}
