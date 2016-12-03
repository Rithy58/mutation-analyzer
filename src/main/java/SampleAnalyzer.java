import major.mutation.Analyzer;
import major.mutation.plugin.*;

public class SampleAnalyzer {
  public static void main(String[] args) {
        System.out.println("Initializing Major Mutation Analyzer...");
        Analyzer analyzer = new Analyzer();

        System.out.println("Loading plugins...");
        // TODO: load plugins

        // TODO: Register plugins

        System.out.println("Running Analyzer...");
        //analyzer.run();
  }
}
