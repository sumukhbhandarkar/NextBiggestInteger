import com.oracle.interview.NextBiggestNumber;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestNextBiggestNumber {
    private static NextBiggestNumber nextBiggestNumber;

    @BeforeClass
    public static void setup() {
        nextBiggestNumber = new NextBiggestNumber();
    }

    @Test
    public void testDescendingOrder() {
        assertEquals("9632", nextBiggestNumber.evaluate("9632"));
        assertEquals("0", nextBiggestNumber.evaluate("0"));
        assertEquals("876543210", nextBiggestNumber.evaluate("876543210"));
    }

    @Test
    public void testNextBiggestNumber() {
        assertEquals("1243", nextBiggestNumber.evaluate("1234"));
        assertEquals("12345678900010", nextBiggestNumber.evaluate("12345678900001"));
        assertEquals("00000010", nextBiggestNumber.evaluate("00000001"));
        assertEquals("989898989889", nextBiggestNumber.evaluate("989898989898"));
    }
}
