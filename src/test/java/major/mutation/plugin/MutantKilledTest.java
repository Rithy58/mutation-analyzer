import major.mutation.plugin.MutantKilled;
import org.junit.Test;
import static org.junit.Assert.*;

public class MutantKilledTest {
    @Test
    public void testMutantKilled() {
        MutantKilled result = new MutantKilled();
        result.loadResult(1);
        result.calculate();
        result.outputResult();
        assertNotNull("MutantKilled failed to instantiate", result);
    }
}
