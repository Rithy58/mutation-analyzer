package major.mutation.plugin.util;

import java.util.concurrent.TimeUnit;
import java.util.*;

import org.junit.internal.runners.statements.Fail;
import org.junit.rules.Timeout;
import org.junit.runner.Description;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.junit.runners.*;

public class TimeoutRunner extends BlockJUnit4ClassRunner {

    private Timeout timeoutRule;

    public TimeoutRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
        timeoutRule = new Timeout(1000);
    }

    @Override
    public List<FrameworkMethod> getChildren() {
        return super.getChildren();
    }
		
    public boolean runTimeoutChild(FrameworkMethod method) {
        Description description = describeChild(method);
        Statement statement;
        try {
            statement = methodBlock(method);
            // Apply timeout rule
            statement = timeoutRule.apply(statement, description);
        } catch (Throwable ex) {
            statement = new Fail(ex);
        }
        try {
            statement.evaluate();
        } catch (Throwable e) {
            return true;
        }
        return false;
    }

}
