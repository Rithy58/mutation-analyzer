import junit.framework.TestCase;
import static triangle.Triangle.TriangleType.*;

public class TestSuite extends TestCase {
   public void test1() {
        assertEquals (triangle.Triangle.classify(0,1301,1), INVALID);
   }
   public void test2() {
        assertEquals (triangle.Triangle.classify(1108,1,1), INVALID);
   }
   public void test3() {
        assertEquals (triangle.Triangle.classify(1,0,-665), INVALID);
   }
   public void test4() {
        assertEquals (triangle.Triangle.classify(1,1,0), INVALID);
   }
   public void test5() {
        assertEquals (triangle.Triangle.classify(582,582,582), EQUILATERAL);
   }
   public void test6() {
        assertEquals (triangle.Triangle.classify(1,1088,15), INVALID);
   }
   public void test7() {
        assertEquals (triangle.Triangle.classify(1,2,450), INVALID);
   }
   public void test8() {
        assertEquals (triangle.Triangle.classify(1663,1088,823), SCALENE);
   }
   public void test9() {
        assertEquals (triangle.Triangle.classify(1187,1146,1), INVALID);
   }
   public void test10() {
        assertEquals (triangle.Triangle.classify(1640,1640,1956), ISOSCELES);
   }
}
