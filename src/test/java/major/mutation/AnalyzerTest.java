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
        TestSuiteBuilder testLoader = new MultiTestLoader("");
        TestSuiteBuilder singleTestLoader = new SingleTestLoader("");
        TestSuiteRunner testRunner = new CoverageRunner();
        ScoreCalculator testResult = new MutantKilled();

        analyzer.registerTestSuiteBuilder(testLoader);
        analyzer.registerTestSuiteRunner(testRunner);
        analyzer.registerScoreCalculator(testResult);
    }
}
