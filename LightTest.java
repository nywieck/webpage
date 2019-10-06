import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class LightTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class LightTest
{
    /**
     * Default constructor for test class LightTest
     */
    public LightTest()
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
    public void testConstructorLight() {
        Light test = new Light(.125, 0.0);
        assertEquals(.125, test.getWidth(), .01);
        assertEquals(0.0, test.getHeight(), .01);
        assertEquals(Curtains.Lining.LIGHT, test.getLining());
        assertEquals(1.5, test.getRValue(), .01);
        assertEquals(1, test.getOps().length);
        assertEquals(WindowCoverings.Ops.SLIDE, test.getOps()[0]);
        assertEquals(9 * (.125 / 12), test.getPriceTotal(), .01);
    }
    
    @Test
    public void testGetPriceMatSpecLight() {
        Light testNormal = new Light(45.5, 20.5);
        Light testWidthZero = new Light(0.0, 20.5);
        Light testHeightZero = new Light(45.5, 0.0);
        Light testZeros = new Light(0.0, 0.0);
        assertEquals(9.0 * (45.5 / 12) * (20.5 / 12), testNormal.getPriceMat(), .01);
        assertEquals(9.0 * (0.0 / 12) * (20.5 / 12), testWidthZero.getPriceMat(), .01);
        assertEquals(9.0 * (45.5 / 12) * (0.0 / 12), testHeightZero.getPriceMat(), .01);
        assertEquals(9.0 * (0.0 / 12) * (0.0 / 12), testZeros.getPriceMat(), .01);
    }
    
    @Test
    public void testEqualityLightLevel() {
        Light test = new Light(11.0, 100.5);
        assertTrue(test.equals(new Light(11.0, 100.5)));
        assertFalse(test.equals(new Light(19.25, 100.5)));
        assertFalse(test.equals(new Light(11.0, .125)));
    }
}