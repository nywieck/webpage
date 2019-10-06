import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class NoneTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class NoneTest
{
    /**
     * Default constructor for test class NoneTest
     */
    public NoneTest()
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
    public void testConstructorNone() {
        None test = new None(0.0, 47.75);
        assertEquals(0.0, test.getWidth(), .01);
        assertEquals(47.75, test.getHeight(), .01);
        assertEquals(Curtains.Lining.NONE, test.getLining());
        assertEquals(1.1, test.getRValue(), .01);
        assertEquals(1, test.getOps().length);
        assertEquals(WindowCoverings.Ops.SLIDE, test.getOps()[0]);
        assertEquals(0.0, test.getPriceTotal(), .01);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testConstructorPreconNeg() {
        None test = new None(-50.0, -98.125);
    }
    
    @Test
    public void testGetPriceMatSpecNone() {
        None testNormal = new None(32.0, 17.25);
        None testWidthZero = new None(0.0, 17.25);
        None testHeightZero = new None(32.0, 0.0);
        None testZeros = new None(0.0, 0.0);
        assertEquals(7.0 * (32.0 / 12) * (17.25 / 12), testNormal.getPriceMat(), .01);
        assertEquals(7.0 * (0.0 / 12) * (17.25 / 12), testWidthZero.getPriceMat(), .01);
        assertEquals(7.0 * (32.0 / 12) * (0.0 / 12), testHeightZero.getPriceMat(), .01);
        assertEquals(7.0 * (0.0 / 12) * (0.0 / 12), testZeros.getPriceMat(), .01);
    }
    
    @Test
    public void testEqualityNoneLevel() {
        None test = new None(10.0, 20.5);
        assertTrue(test.equals(new None(10.0, 20.5)));
        assertFalse(test.equals(new None(99.5, 20.5)));
        assertFalse(test.equals(new None(10.0, 0.0)));
    }
    
    @Test
    public void testEqualityCurtainsLevelToNoneLevel() {
        Curtains noneTest = new None(10.5, 53.0);
        Curtains lightTest = new Light(10.5, 53.0);
        Curtains normalTest = new Normal(10.5, 53.0);
        Curtains heavyTest = new Heavy(10.5, 53.0);
        assertTrue(noneTest.equals(new None(10.5, 53.0)));
        assertTrue(lightTest.equals(new Light(10.5, 53.0)));
        assertTrue(normalTest.equals(new Normal(10.5, 53.0)));
        assertTrue(heavyTest.equals(new Heavy(10.5, 53.0)));
        assertFalse(noneTest.equals(lightTest));
        assertFalse(noneTest.equals(normalTest));
        assertFalse(noneTest.equals(heavyTest));
        assertFalse(lightTest.equals(heavyTest));
        assertFalse(lightTest.equals(normalTest));
        assertFalse(normalTest.equals(heavyTest));
    }
    
    @Test
    public void testEqualityWindowCoveringsLevelToNoneLevel() {
        WindowCoverings noneLevelTest = new None(12.5, 53.75);
        WindowCoverings lightLevelTest = new Light(12.5, 53.75);
        WindowCoverings normalLevelTest = new Normal(12.5, 53.75);
        WindowCoverings heavyLevelTest = new Heavy(12.5, 53.75);
        assertTrue(noneLevelTest.equals(new None(12.5, 53.75)));
        assertTrue(lightLevelTest.equals(new Light(12.5, 53.75)));
        assertTrue(normalLevelTest.equals(new Normal(12.5, 53.75)));
        assertTrue(heavyLevelTest.equals(new Heavy(12.5, 53.75)));
        assertFalse(noneLevelTest.equals(lightLevelTest));
        assertFalse(noneLevelTest.equals(normalLevelTest));
        assertFalse(noneLevelTest.equals(heavyLevelTest));
        assertFalse(lightLevelTest.equals(heavyLevelTest));
        assertFalse(lightLevelTest.equals(normalLevelTest));
        assertFalse(normalLevelTest.equals(heavyLevelTest));
    }
}
