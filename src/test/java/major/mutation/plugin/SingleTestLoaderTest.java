import major.mutation.plugin.SingleTestLoader;
import org.junit.Test;
import static org.junit.Assert.*;

public class SingleTestLoaderTest {
    @Test
    public void testMutantKilled() {
        SingleTestLoader testLoader = new SingleTestLoader("");
        assertFalse(testLoader.loadTest());

        testLoader = new SingleTestLoader("SingleTestLoaderTest");
        assertTrue(testLoader.loadTest());

        testLoader.setupTest();

        assertNotNull(testLoader.getTestSuite());

        assertNotNull("SingleTestLoader failed to instantiate", testLoader);
    }
}
