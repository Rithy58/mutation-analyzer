import major.mutation.Config;
import triangle.Triangle;

import java.util.List;
import org.junit.runner.*;
import org.junit.runner.notification.Failure;

public class Analyzer{
    public static void main(String ... args){

        // Execute original version and gather coverage information
        Config.__M_NO=0;

        if(JUnitCore.runClasses(TestSuite.class).getFailureCount() > 0) {
          System.out.println("TestSuite Failed\n\nFailed TestCases:");

          for(Failure f : JUnitCore.runClasses(TestSuite.class).getFailures()) {
            System.out.println(f.getTestHeader());
            System.out.println(f.getTrace());
          }

          System.exit(-1);
        }

        System.out.println("Covered mutants:");
        // Get List of all covered mutants
        List<Integer> l = Config.getCoverageList();
        // Print all covered mutants
        for(Integer mut : l){
            System.out.print(mut+" ");
        }
        // Reset mutation coverage information
        Config.reset();

        int mutantKilled = 0;

        // Iterate over covered mutants
        for(Integer mut : l) {
            // Enable a certain mutant
            Config.__M_NO=mut;
            if(JUnitCore.runClasses(TestSuite.class).getFailureCount() > 0) {
              mutantKilled++;
            }
        }
        System.out.println("\nMutants killed: " + mutantKilled);
    }
}
