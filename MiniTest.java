import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class MiniTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MiniTest
{
    /**
     * Default constructor for test class MiniTest
     */
    public MiniTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void testConstructorMini() {
        Mini test = new Mini(8.25, 10.0);
        assertEquals(8.25, test.getWidth(), .01);
        assertEquals(10.0, test.getHeight(), .01);
        assertEquals(1, test.getDepth());
        assertEquals(1.4, test.getRValue(), .01);
        assertEquals(3, test.getOps().length);
        assertEquals(WindowCoverings.Ops.RAISE, test.getOps()[0]);
        assertEquals(WindowCoverings.Ops.LOWER, test.getOps()[1]);
        assertEquals(WindowCoverings.Ops.TILT, test.getOps()[2]);
        assertEquals((21.0 * (8.25 / 12)) + (13.0 * (8.25 / 12) * (10.0 / 12)), test.getPriceTotal(), .01);
    }
    
    @Test
    public void testGetPriceHeadMini() {
        Mini testNormal = new Mini(45.5, 7.25);
        Mini testWidthZero = new Mini(0.0, 7.25);
        assertEquals(21.0 * (45.5 / 12), testNormal.getPriceHead(), .01);
        assertEquals(21.0 * (0.0 / 12), testWidthZero.getPriceHead(), .01);
    }
    
    @Test
    public void testGetPriceMatSpec() {
        Mini testNormal = new Mini(24.25, 24.0);
        Mini testWidthZero = new Mini(0.0, 24.0);
        Mini testHeightZero = new Mini(25.25, 0.0);
        Mini testZeros = new Mini(0.0, 0.0);
        assertEquals(13.0 * (24.25 / 12) * (24.0 / 12), testNormal.getPriceMatSpec(), .01);
        assertEquals(13.0 * (0.0 / 12) * (24.0 / 12), testWidthZero.getPriceMatSpec(), .01);
        assertEquals(13.0 * (24.25 / 12) * (0.0 / 12), testHeightZero.getPriceMatSpec(), .01);
        assertEquals(13.0 * (0.0 / 12) * (0.0 / 12), testZeros.getPriceMatSpec(), .01);
    }
    
    @Test
    public void testEqualityMiniLevel() {
        Mini test = new Mini(79.5, 56.25);
        assertTrue(test.equals(new Mini(79.5, 56.25)));
        assertFalse(test.equals(new Mini(25.125, 56.25)));
        assertFalse(test.equals(new Mini(79.5, 1.75)));
    }
}