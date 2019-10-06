import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class PlasticTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class PlasticTest
{
    /**
     * Default constructor for test class PlasticTest
     */
    public PlasticTest()
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
    public void testConstructorPlastic() {
        Plastic test = new Plastic(14.5, 0.0);
        assertEquals(14.5, test.getWidth(), .01);
        assertEquals(0.0, test.getHeight(), .01);
        assertEquals(2, test.getDepth());
        assertEquals(1.8, test.getRValue(), .01);
        assertEquals(3, test.getOps().length);
        assertEquals(WindowCoverings.Ops.RAISE, test.getOps()[0]);
        assertEquals(WindowCoverings.Ops.LOWER, test.getOps()[1]);
        assertEquals(WindowCoverings.Ops.TILT, test.getOps()[2]);
        assertEquals(20.0 * (14.5 / 12), test.getPriceTotal(), .01);
    }

    @Test
        public void testGetPriceHeadPlastic() {
        Plastic testNormal = new Plastic(12.5, 12.5);
        Plastic testWidthZero = new Plastic(0.0, 12.5);
        assertEquals(20.0 * (12.5 / 12), testNormal.getPriceHead(), .01);
        assertEquals(20.0 * (0.0 / 12), testWidthZero.getPriceHead(), .01);
    }
    
    @Test
    public void testGetPriceMatSpec() {
        Plastic testNormal = new Plastic(35.5, 37.50);
        Plastic testWidthZero = new Plastic(0.0, 37.50);
        Plastic testHeightZero = new Plastic(25.25, 0.0);
        Plastic testZeros = new Plastic(0.0, 0.0);
        assertEquals(10.0 * (35.5 / 12) * (37.50 / 12), testNormal.getPriceMatSpec(), .01);
        assertEquals(10.0 * (0.0 / 12) * (37.50 / 12), testWidthZero.getPriceMatSpec(), .01);
        assertEquals(10.0 * (35.5 / 12) * (0.0 / 12), testHeightZero.getPriceMatSpec(), .01);
        assertEquals(10.0 * (0.0 / 12) * (0.0 / 12), testZeros.getPriceMatSpec(), .01);
    }
    
    @Test
    public void testEqualityPlasticLevel() {
        Plastic test = new Plastic(100.125, 25.5);
        assertTrue(test.equals(new Plastic(100.125, 25.5)));
        assertFalse(test.equals(new Plastic(5.125, 25.5)));
        assertFalse(test.equals(new Plastic(100.125, 1.5)));
    }
}
