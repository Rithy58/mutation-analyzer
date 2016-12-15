import major.mutation.Analyzer;
import major.mutation.plugin.coverage.CoverageRunner;
import major.mutation.plugin.*;

import java.util.*;
import java.io.*;
import java.nio.charset.*;

public class NumericsAnalyzer {
  public static void main(String[] args) {
        System.out.println("Initializing Major Mutation Analyzer...");
        Analyzer analyzer = new Analyzer();

        System.out.println("Loading plugins...");
        // TODO: load plugins
        // TestSuiteBuilder testLoader = new SingleTestLoader("TestSuite");
        List<String> tests = new ArrayList<>();
        try (
            InputStream fis = new FileInputStream("testMap.csv");
            InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
            BufferedReader br = new BufferedReader(isr);
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() > 0) {
                  tests.add(line);
                }
            }
        } catch (Exception e) {
          e.printStackTrace();
        }
        String[] testSuites = new String[tests.size()];
        tests.toArray(testSuites);
        TestSuiteBuilder testLoader = new MultiTestLoader(testSuites);
        TestSuiteRunner testRunner = new CoverageRunner();
        ScoreCalculator testResult = new MutantKilled();

        // TODO: Register plugins
        analyzer.registerTestSuiteBuilder(testLoader);
        analyzer.registerTestSuiteRunner(testRunner);
        analyzer.registerScoreCalculator(testResult);

        System.out.println("Running Analyzer...");
        analyzer.run();
        System.exit(1);
  }
}
