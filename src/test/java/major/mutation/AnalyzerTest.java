import org.junit.Test;
import static org.junit.Assert.*;

public class AnalyzerTest {
    @Test
    public void testAnalyzer() {
        Analyzer analyzer = new Analyzer();
        assertNotNull("Analyzer failed to instantiate", analyzer);
    }
}
