import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class WoodTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class WoodTest
{
    /**
     * Default constructor for test class WoodTest
     */
    public WoodTest()
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
    public void testConstructorWood() {
        Wood test = new Wood(0.0, 178.125);
        assertEquals(0.0, test.getWidth(), .01);
        assertEquals(178.125, test.getHeight(), .01);
        assertEquals(3, test.getDepth());
        assertEquals(1.6, test.getRValue(), .01);
        assertEquals(3, test.getOps().length);
        assertEquals(WindowCoverings.Ops.RAISE, test.getOps()[0]);
        assertEquals(WindowCoverings.Ops.LOWER, test.getOps()[1]);
        assertEquals(WindowCoverings.Ops.TILT, test.getOps()[2]);
        assertEquals(0.0, test.getPriceTotal(), .01);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testConstructorPreconNeg() {
        Wood test = new Wood(-0.125, 100.0);
    }

    @Test
    public void testGetPriceHeadWood() {
        Wood testNormal = new Wood(15.5, 17.5);
        Wood testWidthZero = new Wood(0.0, 17.5);
        assertEquals(22.0 * (15.5 / 12), testNormal.getPriceHead(), .01);
        assertEquals(22.0 * (0.0 / 12), testWidthZero.getPriceHead(), .01);
    }
    
    @Test
    public void testGetPriceMatSpecWood() {
        Wood testNormal = new Wood(25.25, 27.0);
        Wood testWidthZero = new Wood(0.0, 27.0);
        Wood testHeightZero = new Wood(25.25, 0.0);
        Wood testZeros = new Wood(0.0, 0.0);
        assertEquals(16.0 * (25.25 / 12) * (27.0 / 12), testNormal.getPriceMatSpec(), .01);
        assertEquals(16.0 * (0.0 / 12) * (27.0 / 12), testWidthZero.getPriceMatSpec(), .01);
        assertEquals(16.0 * (25.25 / 12) * (0.0 / 12), testHeightZero.getPriceMatSpec(), .01);
        assertEquals(16.0 * (0.0 / 12) * (0.0 / 12), testZeros.getPriceMatSpec(), .01);
    }
    
    @Test
    public void testEqualityWoodLevel() {
        Wood test = new Wood(10.0, 10.0);        
        assertTrue(test.equals(new Wood(10.0, 10.0)));
        assertFalse(test.equals(new Wood(5.125, 10.0)));
        assertFalse(test.equals(new Wood(10.0, 1.5)));
    }
    
    @Test
    public void testEqualitySlattedLevelToWoodLevel() {
        Slatted woodTest = new Wood(100.0, 50.5);
        Slatted plasticTest = new Plastic(100.0, 50.5);
        Slatted miniTest = new Mini(100.0, 50.5);        
        assertTrue(woodTest.equals(new Wood(100.0, 50.5)));
        assertTrue(plasticTest.equals(new Plastic(100.0, 50.5)));
        assertTrue(miniTest.equals(new Mini(100.0, 50.5)));
        assertFalse(woodTest.equals(plasticTest));
        assertFalse(woodTest.equals(miniTest));
        assertFalse(miniTest.equals(plasticTest));
    }
    
    @Test
    public void testEqualityWindowCoveringsLevelToWoodLevel() {
        WindowCoverings woodLevelTest = new Wood(100.0, 50.5);
        WindowCoverings plasticLevelTest = new Plastic(100.0, 50.5);
        WindowCoverings miniLevelTest = new Mini(100.0, 50.5);
        assertTrue(woodLevelTest.equals(new Wood(100.0, 50.5)));
        assertTrue(plasticLevelTest.equals(new Plastic(100.0, 50.5)));
        assertTrue(miniLevelTest.equals(new Mini(100.0, 50.5)));
        assertFalse(woodLevelTest.equals(plasticLevelTest));
        assertFalse(woodLevelTest.equals(miniLevelTest));
        assertFalse(miniLevelTest.equals(plasticLevelTest));
    }
    
    @Test
    public void testEqualityWindowCoveringsLevelToSlattedLevel() {
        WindowCoverings slattedLevelTest = new Wood(10.0, 10.0);
        WindowCoverings honeycombLevelTest = new Blackout(10.0, 10.0, Honeycomb.Cord.CONTINUOUS, Honeycomb.Side.RIGHT);
        WindowCoverings curtainsLevelTest = new Light(10.0, 10.0);
        assertTrue(slattedLevelTest.equals(new Wood(10.0, 10.0)));
        assertTrue(honeycombLevelTest.equals(new Blackout(10.0, 10.0, Honeycomb.Cord.CONTINUOUS, Honeycomb.Side.RIGHT)));
        assertTrue(curtainsLevelTest.equals(new Light(10.0, 10.0)));       
        assertFalse(slattedLevelTest.equals(honeycombLevelTest));
        assertFalse(slattedLevelTest.equals(curtainsLevelTest));
        assertFalse(honeycombLevelTest.equals(curtainsLevelTest));
    }
}