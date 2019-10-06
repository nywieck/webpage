import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class BlackoutTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class BlackoutTest
{
    /**
     * Default constructor for test class BlackoutTest
     */
    public BlackoutTest()
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
    public void testConstructorBlackout() {
        Blackout test = new Blackout(100.0, 47.75, Honeycomb.Cord.CONTINUOUS, Honeycomb.Side.RIGHT);
        assertEquals(100.0, test.getWidth(), .01);
        assertEquals(47.75, test.getHeight(), .01);
        assertEquals(Honeycomb.Cord.CONTINUOUS, test.getCord());
        assertEquals(Honeycomb.Side.RIGHT, test.getSide());
        assertEquals(3.9, test.getRValue(), .01);
        assertEquals(2, test.getOps().length);
        assertEquals(WindowCoverings.Ops.RAISE, test.getOps()[0]);
        assertEquals(WindowCoverings.Ops.LOWER, test.getOps()[1]);
        assertEquals((21.0 * (100.0 / 12)) + (16.0 * (100.0 / 12) * (47.75 / 12)), test.getPriceTotal(), .01);
    }
    
    @Test
    public void testGetPriceHeadBlackout() {
        Blackout testNormalPull = new Blackout(51.5, 20.25, Honeycomb.Cord.PULL, Honeycomb.Side.LEFT);
        Blackout testNormalContinuous = new Blackout(51.5, 20.25, Honeycomb.Cord.CONTINUOUS, Honeycomb.Side.LEFT);
        Blackout testWidthZero = new Blackout(0.0, 10.25, Honeycomb.Cord.PULL, Honeycomb.Side.LEFT);        
        assertEquals(19.0 * (51.5 / 12), testNormalPull.getPriceHead(), .01);
        assertEquals(21.0 * (51.5 / 12), testNormalContinuous.getPriceHead(), .01);
        assertEquals(19.0 * (0.0 / 12), testWidthZero.getPriceHead(), .01);
    }
    
    @Test
    public void testGetPriceMatSpecBlackout() {
        Blackout testNormalPull = new Blackout(49.25, 21.0, Honeycomb.Cord.PULL, Honeycomb.Side.LEFT);
        Blackout testNormalContinuous = new Blackout(49.25, 21.0, Honeycomb.Cord.CONTINUOUS, Honeycomb.Side.LEFT);
        Blackout testWidthZero = new Blackout(0.0, 21.0, Honeycomb.Cord.PULL, Honeycomb.Side.LEFT);
        Blackout testHeightZero = new Blackout(29.25, 0.0, Honeycomb.Cord.PULL, Honeycomb.Side.LEFT);
        Blackout testZeros = new Blackout(0.0, 0.0, Honeycomb.Cord.PULL, Honeycomb.Side.LEFT);
        assertEquals(16.0 * (49.25 / 12) * (21.0 / 12), testNormalPull.getPriceMatSpec(), .01);
        assertEquals(16.0 * (49.25 / 12) * (21.0 / 12), testNormalContinuous.getPriceMatSpec(), .01);
        assertEquals(16.0 * (0.0 / 12) * (21.0 / 12), testWidthZero.getPriceMatSpec(), .01);
        assertEquals(16.0 * (49.25 / 12) * (0.0 / 12), testHeightZero.getPriceMatSpec(), .01);
        assertEquals(16.0 * (0.0 / 12) * (0.0 / 12), testZeros.getPriceMatSpec(), .01);
    }
    
    @Test
    public void testEqualityBlackoutLevel() {
        Blackout test = new Blackout(55.5, 75.25, Honeycomb.Cord.CONTINUOUS, Honeycomb.Side.RIGHT);
        assertTrue(test.equals(new Blackout(55.5, 75.25, Honeycomb.Cord.CONTINUOUS, Honeycomb.Side.RIGHT)));
        assertFalse(test.equals(new Blackout(0.25, 75.25, Honeycomb.Cord.CONTINUOUS, Honeycomb.Side.RIGHT)));
        assertFalse(test.equals(new Blackout(55.5, 100.5, Honeycomb.Cord.CONTINUOUS, Honeycomb.Side.RIGHT)));
        assertFalse(test.equals(new Blackout(55.5, 75.25, Honeycomb.Cord.PULL, Honeycomb.Side.RIGHT)));
        assertFalse(test.equals(new Blackout(55.5, 75.25, Honeycomb.Cord.CONTINUOUS, Honeycomb.Side.LEFT)));
    }
}