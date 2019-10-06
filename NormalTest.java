import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class NormalTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class NormalTest
{
    /**
     * Default constructor for test class NormalTest
     */
    public NormalTest()
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
    public void testConstructorNormal() {
        Normal test = new Normal(10.5, 25.125);
        assertEquals(10.5, test.getWidth(), .01);
        assertEquals(25.125, test.getHeight(), .01);
        assertEquals(Curtains.Lining.NORMAL, test.getLining());
        assertEquals(1.8, test.getRValue(), .01);
        assertEquals(1, test.getOps().length);
        assertEquals(WindowCoverings.Ops.SLIDE, test.getOps()[0]);
        assertEquals((9.0 * (10.5 / 12)) + (10.0 * (10.5 / 12) * (25.125 / 12)), test.getPriceTotal(), .01);
    }

    @Test
    public void testGetPriceMatSpecLightNormal() {
        Normal testNormal = new Normal(30.5, 19.5);
        Normal testWidthZero = new Normal(0.0, 19.5);
        Normal testHeightZero = new Normal(30.5, 0.0);
        Normal testZeros = new Normal(0.0, 0.0);
        assertEquals(10.0 * (30.5 / 12) * (19.5 / 12), testNormal.getPriceMat(), .01);
        assertEquals(10.0 * (0.0 / 12) * (19.5 / 12), testWidthZero.getPriceMat(), .01);
        assertEquals(10.0 * (30.5 / 12) * (0.0 / 12), testHeightZero.getPriceMat(), .01);
        assertEquals(10.0 * (0.0 / 12) * (0.0 / 12), testZeros.getPriceMat(), .01);
    }
    
    @Test
    public void testEqualityNormalLevel() {
        Normal test = new Normal(15.0, 23.5);
        assertTrue(test.equals(new Normal(15.0, 23.5)));
        assertFalse(test.equals(new Normal(35.0, 23.5)));
        assertFalse(test.equals(new Normal(15.0, 23.375)));
    }
}