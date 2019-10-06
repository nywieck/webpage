import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class SingleTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SingleTest
{
    /**
     * Default constructor for test class SingleTest
     */
    public SingleTest()
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
    public void testConstructorSingle() {
        Single test = new Single(15.125, 9.5, Honeycomb.Cord.PULL, Honeycomb.Side.LEFT);
        assertEquals(15.125, test.getWidth(), .01);
        assertEquals(9.5, test.getHeight(), .01);
        assertEquals(Honeycomb.Cord.PULL, test.getCord());
        assertEquals(Honeycomb.Side.LEFT, test.getSide());
        assertEquals(2.2, test.getRValue(), .01);
        assertEquals(2, test.getOps().length);
        assertEquals(WindowCoverings.Ops.RAISE, test.getOps()[0]);
        assertEquals(WindowCoverings.Ops.LOWER, test.getOps()[1]);
        assertEquals((16.0 * (15.125 / 12)) + (12.0 * (15.125 / 12) * (9.5 / 12)), test.getPriceTotal(), .01);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testConstructorPreconNeg() {
        Single test = new Single(100.5, -98.125, Honeycomb.Cord.PULL, Honeycomb.Side.LEFT);
    }

    @Test
    public void testGetPriceHeadSingle() {
        Single testNormalPull = new Single(55.5, 10.25, Honeycomb.Cord.PULL, Honeycomb.Side.LEFT);
        Single testNormalContinuous = new Single(55.5, 10.25, Honeycomb.Cord.CONTINUOUS, Honeycomb.Side.LEFT);
        Single testWidthZero = new Single(0.0, 10.25, Honeycomb.Cord.PULL, Honeycomb.Side.LEFT);        
        assertEquals(16.0 * (55.5 / 12), testNormalPull.getPriceHead(), .01);
        assertEquals(17.0 * (55.5 / 12), testNormalContinuous.getPriceHead(), .01);
        assertEquals(16.0 * (0.0 / 12), testWidthZero.getPriceHead(), .01);
    }
    
    @Test
    public void testGetPriceMatSpecSingle() {
        Single testNormalPull = new Single(29.25, 21.25, Honeycomb.Cord.PULL, Honeycomb.Side.LEFT);
        Single testNormalContinuous = new Single(29.25, 21.25, Honeycomb.Cord.CONTINUOUS, Honeycomb.Side.LEFT);
        Single testWidthZero = new Single(0.0, 21.25, Honeycomb.Cord.PULL, Honeycomb.Side.LEFT);
        Single testHeightZero = new Single(29.25, 0.0, Honeycomb.Cord.PULL, Honeycomb.Side.LEFT);
        Single testZeros = new Single(0.0, 0.0, Honeycomb.Cord.PULL, Honeycomb.Side.LEFT);
        assertEquals(12.0 * (29.25 / 12) * (21.25 / 12), testNormalPull.getPriceMatSpec(), .01);
        assertEquals(12.0 * (29.25 / 12) * (21.25 / 12), testNormalContinuous.getPriceMatSpec(), .01);
        assertEquals(12.0 * (0.0 / 12) * (21.25 / 12), testWidthZero.getPriceMatSpec(), .01);
        assertEquals(12.0 * (29.25 / 12) * (0.0 / 12), testHeightZero.getPriceMatSpec(), .01);
        assertEquals(12.0 * (0.0 / 12) * (0.0 / 12), testZeros.getPriceMatSpec(), .01);
    }
    
    @Test
    public void testEqualitySingleLevel() {
        Single test = new Single(16.125, 5.75, Honeycomb.Cord.PULL, Honeycomb.Side.LEFT);
        assertTrue(test.equals(new Single(16.125, 5.75, Honeycomb.Cord.PULL, Honeycomb.Side.LEFT)));
        assertFalse(test.equals(new Single(0.0, 5.75, Honeycomb.Cord.PULL, Honeycomb.Side.LEFT)));
        assertFalse(test.equals(new Single(16.125, 25.0, Honeycomb.Cord.PULL, Honeycomb.Side.LEFT)));
        assertFalse(test.equals(new Single(16.125, 5.75, Honeycomb.Cord.CONTINUOUS, Honeycomb.Side.LEFT)));
        assertFalse(test.equals(new Single(16.125, 5.75, Honeycomb.Cord.PULL, Honeycomb.Side.RIGHT)));
    }
    
    @Test
    public void testEqualityHoneycombLevelToSingleLevel() {
        Honeycomb singleTest = new Single(15.5, 4.75, Honeycomb.Cord.CONTINUOUS, Honeycomb.Side.RIGHT);
        Honeycomb doubleTest = new Double(15.5, 4.75, Honeycomb.Cord.CONTINUOUS, Honeycomb.Side.RIGHT);
        Honeycomb blackoutTest = new Blackout(15.5, 4.75, Honeycomb.Cord.CONTINUOUS, Honeycomb.Side.RIGHT);
        assertTrue(singleTest.equals(new Single(15.5, 4.75, Honeycomb.Cord.CONTINUOUS, Honeycomb.Side.RIGHT)));
        assertTrue(doubleTest.equals(new Double(15.5, 4.75, Honeycomb.Cord.CONTINUOUS, Honeycomb.Side.RIGHT)));
        assertTrue(blackoutTest.equals(new Blackout(15.5, 4.75, Honeycomb.Cord.CONTINUOUS, Honeycomb.Side.RIGHT)));
        assertFalse(singleTest.equals(doubleTest));
        assertFalse(singleTest.equals(blackoutTest));
        assertFalse(doubleTest.equals(blackoutTest));        
    }
    
    @Test
    public void testEqualityWindowCoveringsLevelToSingleLevel() {
        WindowCoverings singleLevelTest = new Single(25.0, 10.5, Honeycomb.Cord.CONTINUOUS, Honeycomb.Side.RIGHT);
        WindowCoverings doubleLevelTest = new Double(25.0, 10.5, Honeycomb.Cord.CONTINUOUS, Honeycomb.Side.RIGHT);
        WindowCoverings blackoutLevelTest = new Blackout(25.0, 10.5, Honeycomb.Cord.CONTINUOUS, Honeycomb.Side.RIGHT);
        assertTrue(singleLevelTest.equals(new Single(25.0, 10.5, Honeycomb.Cord.CONTINUOUS, Honeycomb.Side.RIGHT)));
        assertTrue(doubleLevelTest.equals(new Double(25.0, 10.5, Honeycomb.Cord.CONTINUOUS, Honeycomb.Side.RIGHT)));
        assertTrue(blackoutLevelTest.equals(new Blackout(25.0, 10.5, Honeycomb.Cord.CONTINUOUS, Honeycomb.Side.RIGHT)));
        assertFalse(singleLevelTest.equals(doubleLevelTest));
        assertFalse(doubleLevelTest.equals(blackoutLevelTest));
        assertFalse(blackoutLevelTest.equals(singleLevelTest));        
    }
}