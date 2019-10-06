import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class HeavyTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class HeavyTest
{
    /**
     * Default constructor for test class HeavyTest
     */
    public HeavyTest()
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
    public void testConstructorHeavy() {
        Heavy test = new Heavy(10.5, 25.125);
        assertEquals(10.5, test.getWidth(), .01);
        assertEquals(25.125, test.getHeight(), .01);
        assertEquals(Curtains.Lining.HEAVY, test.getLining());
        assertEquals(2.1, test.getRValue(), .01);
        assertEquals(1, test.getOps().length);
        assertEquals(WindowCoverings.Ops.SLIDE, test.getOps()[0]);
        assertEquals((12.0 * (10.5 / 12)) + (11.0 * (10.5 / 12) * (25.125 / 12)), test.getPriceTotal(), .01);
    }

    @Test
    public void testGetPriceHeadHeavy() {
        Heavy testNormal = new Heavy(16.5, 75.5);
        Heavy testWidthZero = new Heavy(0.0, 75.5);
        assertEquals(12.0 * (16.5 / 12), testNormal.getPriceHead(), .01);
        assertEquals(12.0 * (0.0 / 12), testWidthZero.getPriceHead(), .01);
    }
    
    @Test
    public void testGetPriceMatSpecHeavy() {
        Heavy testNormal = new Heavy(35.0, 9.75);
        Heavy testWidthZero = new Heavy(0.0, 9.75);
        Heavy testHeightZero = new Heavy(35.0, 0.0);
        Heavy testZeros = new Heavy(0.0, 0.0);
        assertEquals(11.0 * (35.0 / 12) * (9.75 / 12), testNormal.getPriceMat(), .01);
        assertEquals(11.0 * (0.0 / 12) * (9.75 / 12), testWidthZero.getPriceMat(), .01);
        assertEquals(11.0 * (35.0 / 12) * (0.0 / 12), testHeightZero.getPriceMat(), .01);
        assertEquals(11.0 * (0.0 / 12) * (0.0 / 12), testZeros.getPriceMat(), .01);
    }
    
    @Test
    public void testEqualityHeavyLevel() {
        Heavy test = new Heavy(83.125, 45.75);
        assertTrue(test.equals(new Heavy(83.125, 45.75)));
        assertFalse(test.equals(new Heavy(83.0, 45.75)));
        assertFalse(test.equals(new Heavy(83.125, 55.75)));
    }
}
