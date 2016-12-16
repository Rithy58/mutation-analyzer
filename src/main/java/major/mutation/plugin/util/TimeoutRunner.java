// package major.mutation.plugin.util;
//
// import java.util.List;
//
// import org.junit.internal.runners.statements.Fail;
// import org.junit.rules.Timeout;
// import org.junit.runner.Description;
// import org.junit.runners.model.FrameworkMethod;
// import org.junit.runners.model.InitializationError;
// import org.junit.runners.model.Statement;
// import org.junit.runners.*;
//
// public class TimeoutRunner {
//
//     private BlockJUnit4ClassRunner runner;
//     private Timeout timeoutRule;
//
//     public TimeoutRunner(Class<?> testClass) throws InitializationError {
//         runner = new BlockJUnit4ClassRunner(testClass);
//
//         timeoutRule = new Timeout(1000);
//     }
//
//     public List<FrameworkMethod> getChildren() {
//         // TODO Auto-generated method stub
//         return runner.getChildren();
//     }
//
//     public boolean runTimeoutChild(FrameworkMethod method) {
//         Description description = runner.describeChild(method);
//         if (!runner.isIgnored(method)) {
//             Statement statement;
//             try {
//                 statement = runner.methodBlock(method);
//                 // Apply timeout rule
//                 statement = timeoutRule.apply(statement, description);
//             } catch (Throwable ex) {
//                 statement = new Fail(ex);
//             }
//             try {
//                 statement.evaluate();
//             } catch (Throwable e) {
//                 return true;
//             }
//         }
//         return false;
//     }
//
// }
