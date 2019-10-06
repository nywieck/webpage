import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class DoubleTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class DoubleTest
{
    /**
     * Default constructor for test class DoubleTest
     */
    public DoubleTest()
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
    public void testConstructorDoubleNoBlackout() {
        Double test = new Double(100.0, 47.75, Honeycomb.Cord.CONTINUOUS, Honeycomb.Side.RIGHT);
        assertEquals(100.0, test.getWidth(), .01);
        assertEquals(47.75, test.getHeight(), .01);
        assertEquals(Honeycomb.Cord.CONTINUOUS, test.getCord());
        assertEquals(Honeycomb.Side.RIGHT, test.getSide());
        assertEquals(2.7, test.getRValue(), .01);
        assertEquals(2, test.getOps().length);
        assertEquals(WindowCoverings.Ops.RAISE, test.getOps()[0]);
        assertEquals(WindowCoverings.Ops.LOWER, test.getOps()[1]);
        assertEquals((21.0 * (100.0 / 12)) + (15.0 * (100.0 / 12) * (47.75 / 12)), test.getPriceTotal(), .01);
    }
    
    @Test
    public void testConstructorDoubleBlackout() {
        Double test = new Double(100.0, 47.75, 3.9, Honeycomb.Cord.CONTINUOUS, Honeycomb.Side.RIGHT);
        assertEquals(100.0, test.getWidth(), .01);
        assertEquals(47.75, test.getHeight(), .01);
        assertEquals(Honeycomb.Cord.CONTINUOUS, test.getCord());
        assertEquals(Honeycomb.Side.RIGHT, test.getSide());
        assertEquals(3.9, test.getRValue(), .01);
        assertEquals(2, test.getOps().length);
        assertEquals(WindowCoverings.Ops.RAISE, test.getOps()[0]);
        assertEquals(WindowCoverings.Ops.LOWER, test.getOps()[1]);
        assertEquals((21.0 * (100.0 / 12)) + (15.0 * (100.0 / 12) * (47.75 / 12)), test.getPriceTotal(), .01);
    }
    
    @Test
    public void testGetPriceHeadDouble() {
        Double testNormalPull = new Double(51.5, 20.25, Honeycomb.Cord.PULL, Honeycomb.Side.LEFT);
        Double testNormalContinuous = new Double(51.5, 20.25, Honeycomb.Cord.CONTINUOUS, Honeycomb.Side.LEFT);
        Double testWidthZero = new Double(0.0, 10.25, Honeycomb.Cord.PULL, Honeycomb.Side.LEFT);        
        assertEquals(19.0 * (51.5 / 12), testNormalPull.getPriceHead(), .01);
        assertEquals(21.0 * (51.5 / 12), testNormalContinuous.getPriceHead(), .01);
        assertEquals(19.0 * (0.0 / 12), testWidthZero.getPriceHead(), .01);
    }
    
    @Test
    public void testGetPriceMatSpecDouble() {
        Double testNormalPull = new Double(49.25, 21.0, Honeycomb.Cord.PULL, Honeycomb.Side.LEFT);
        Double testNormalContinuous = new Double(49.25, 21.0, Honeycomb.Cord.CONTINUOUS, Honeycomb.Side.LEFT);
        Double testWidthZero = new Double(0.0, 21.0, Honeycomb.Cord.PULL, Honeycomb.Side.LEFT);
        Double testHeightZero = new Double(29.25, 0.0, Honeycomb.Cord.PULL, Honeycomb.Side.LEFT);
        Double testZeros = new Double(0.0, 0.0, Honeycomb.Cord.PULL, Honeycomb.Side.LEFT);
        assertEquals(15.0 * (49.25 / 12) * (21.0 / 12), testNormalPull.getPriceMatSpec(), .01);
        assertEquals(15.0 * (49.25 / 12) * (21.0 / 12), testNormalContinuous.getPriceMatSpec(), .01);
        assertEquals(15.0 * (0.0 / 12) * (21.0 / 12), testWidthZero.getPriceMatSpec(), .01);
        assertEquals(15.0 * (49.25 / 12) * (0.0 / 12), testHeightZero.getPriceMatSpec(), .01);
        assertEquals(15.0 * (0.0 / 12) * (0.0 / 12), testZeros.getPriceMatSpec(), .01);
    }
    
    @Test
    public void testEqualityDoubleLevel() {
        Double test = new Double(100.0, 35.75, Honeycomb.Cord.CONTINUOUS, Honeycomb.Side.RIGHT);
        assertTrue(test.equals(new Double(100.0, 35.75, Honeycomb.Cord.CONTINUOUS, Honeycomb.Side.RIGHT)));
        assertFalse(test.equals(new Double(45.0, 35.75, Honeycomb.Cord.CONTINUOUS, Honeycomb.Side.RIGHT)));
        assertFalse(test.equals(new Double(100.0, 12.5, Honeycomb.Cord.CONTINUOUS, Honeycomb.Side.RIGHT)));
        assertFalse(test.equals(new Double(100.0, 35.75, Honeycomb.Cord.PULL, Honeycomb.Side.RIGHT)));
        assertFalse(test.equals(new Double(100.0, 35.75, Honeycomb.Cord.CONTINUOUS, Honeycomb.Side.LEFT)));
    }
}