import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class DinetteOrderTest.
 *
 * @author  Nathaniel Wieck
 */
public class DinetteOrderTest {
    /**
     * Default constructor for test class DinetteOrderTest
     */
    public DinetteOrderTest() {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp() {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown() {
    }

    @Test
    public void testDefaultConstructor() {
        DinetteOrder testDinetteOrder = new DinetteOrder(1);
        assertEquals(1, testDinetteOrder.getOrderNumber());
        assertEquals(4, testDinetteOrder.getChairCount());
        assertEquals(0, testDinetteOrder.getLeafCount());
        assertEquals(DinetteOrder.Option.CLEANING_KIT, testDinetteOrder.getOption());
    }
    
    @Test
    public void testCustomConstructor() {
        DinetteOrder testDinetteOrder = new DinetteOrder(1, 8, 2, DinetteOrder.Option.SEAT_CUSHIONS);
        assertEquals(1, testDinetteOrder.getOrderNumber());
        assertEquals(8, testDinetteOrder.getChairCount());
        assertEquals(2, testDinetteOrder.getLeafCount());
        assertEquals(DinetteOrder.Option.SEAT_CUSHIONS, testDinetteOrder.getOption());
    }
    
    @Test
    public void testAccessors() {
        DinetteOrder testDinetteOrder = new DinetteOrder(1, 2, 2, DinetteOrder.Option.PADDED_FEET);
        assertEquals(DinetteOrder.TABLE_PRICE * 1 + DinetteOrder.CHAIR_PRICE * 2 + DinetteOrder.LEAF_PRICE * 2, testDinetteOrder.getPrice(), .01);
        testDinetteOrder.setChairCount(6);
        testDinetteOrder.setLeafCount(0);
        assertEquals(DinetteOrder.TABLE_PRICE * 1 + DinetteOrder.CHAIR_PRICE * 6 + DinetteOrder.LEAF_PRICE * 0, testDinetteOrder.getPrice(), .01);
        assertEquals(DinetteOrder.Option.PADDED_FEET, testDinetteOrder.getOption());
    }

    @Test
    public void testMutators() {
        DinetteOrder testDinetteOrder = new DinetteOrder(1);
        testDinetteOrder.setChairCount(5);
        assertEquals(5, testDinetteOrder.getChairCount());
        testDinetteOrder.setLeafCount(2);
        assertEquals(2, testDinetteOrder.getLeafCount());
        testDinetteOrder.setOption(DinetteOrder.Option.SEAT_CUSHIONS);
        assertEquals(DinetteOrder.Option.SEAT_CUSHIONS, testDinetteOrder.getOption());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testPreconCustomConstructorChairLow() {
        DinetteOrder testOrder = new DinetteOrder(1, -1, 1, DinetteOrder.Option.CLEANING_KIT);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testPreconCustomConstructorChairHigh() {
        DinetteOrder testOrder = new DinetteOrder(1, 11, 1, DinetteOrder.Option.CLEANING_KIT);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testPreconCustomConstructorLeafLow() {
        DinetteOrder testOrder = new DinetteOrder(1, 1, -1, DinetteOrder.Option.CLEANING_KIT);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testPreconCustomConstructorLeafHigh() {
        DinetteOrder testOrder = new DinetteOrder(1, 1, 3, DinetteOrder.Option.CLEANING_KIT);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testPreconSetChairCountLow() {
        DinetteOrder testOrder = new DinetteOrder(1);
        testOrder.setChairCount(-1);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testPreconSetChairCountHigh() {
        DinetteOrder testOrder = new DinetteOrder(1);
        testOrder.setChairCount(11);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testPreconSetLeafCountLow() {
        DinetteOrder testOrder = new DinetteOrder(1);
        testOrder.setLeafCount(-1);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testPreconSetLeafCountHigh() {
        DinetteOrder testOrder = new DinetteOrder(1);
        testOrder.setLeafCount(3);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testPreconSetOptionNull() {
        DinetteOrder testOrder = new DinetteOrder(1);
        testOrder.setOption(null);
    }
}
