import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class DinetteStoreTest.
 *
 * @author  Nathaniel Wieck
 */
public class DinetteStoreTest {
    /**
     * Default constructor for test class DinetteStoreTest
     */
    public DinetteStoreTest() {
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
    public void testConstructor() {
        DinetteStore testDinetteStore = new DinetteStore(1, 2, 1000);
        assertEquals(1, testDinetteStore.getTableInventory());
        assertEquals(2, testDinetteStore.getChairInventory());
        assertEquals(1000, testDinetteStore.getLeafInventory());
        assertEquals(0, testDinetteStore.getTablesOnOrder());
        assertEquals(0, testDinetteStore.getChairsOnOrder());
        assertEquals(0, testDinetteStore.getLeavesOnOrder());
        assertEquals(0, testDinetteStore.getTotalSales(), .01);
        assertEquals(0, testDinetteStore.getAvgOrderPrice(), .01);
    }

    @Test
    public void testGetAvgOrderPrice() {
        DinetteStore testDinetteStore = new DinetteStore(100, 100, 100);
        DinetteOrder testOrder = new DinetteOrder(1, 4, 2, DinetteOrder.Option.CLEANING_KIT);
        testDinetteStore.submitOrder(testOrder);
        DinetteOrder testOrder2 = new DinetteOrder(2, 8, 1, DinetteOrder.Option.CLEANING_KIT);
        testDinetteStore.submitOrder(testOrder2);
        assertEquals((DinetteOrder.TABLE_PRICE * 2 + DinetteOrder.CHAIR_PRICE * 12 + DinetteOrder.LEAF_PRICE * 3)/2, testDinetteStore.getAvgOrderPrice(), 0.01);
    }
    
    @Test
    public void testSubmitOrder() {
        DinetteStore testDinetteStore = new DinetteStore(10, 10, 10);
        DinetteOrder testOrder1 = new DinetteOrder(1, 4, 2, DinetteOrder.Option.CLEANING_KIT);
        testDinetteStore.submitOrder(testOrder1);
        assertEquals(9, testDinetteStore.getTableInventory());
        assertEquals(6, testDinetteStore.getChairInventory());
        assertEquals(8, testDinetteStore.getLeafInventory());
        assertEquals(1, testDinetteStore.getTablesOnOrder());
        assertEquals(4, testDinetteStore.getChairsOnOrder());
        assertEquals(2, testDinetteStore.getLeavesOnOrder());
        assertEquals(DinetteOrder.TABLE_PRICE * 1 + DinetteOrder.CHAIR_PRICE * 4 + DinetteOrder.LEAF_PRICE * 2, testDinetteStore.getTotalSales(), .01);
        assertEquals((DinetteOrder.TABLE_PRICE * 1 + DinetteOrder.CHAIR_PRICE * 4 + DinetteOrder.LEAF_PRICE * 2)/1, testDinetteStore.getAvgOrderPrice(), .01);
        DinetteOrder testOrder2 = new DinetteOrder(2, 2, 1, DinetteOrder.Option.CLEANING_KIT);
        testDinetteStore.submitOrder(testOrder2);
        assertEquals(8, testDinetteStore.getTableInventory());
        assertEquals(4, testDinetteStore.getChairInventory());
        assertEquals(7, testDinetteStore.getLeafInventory());
        assertEquals(2, testDinetteStore.getTablesOnOrder());
        assertEquals(6, testDinetteStore.getChairsOnOrder());
        assertEquals(3, testDinetteStore.getLeavesOnOrder());
        assertEquals(DinetteOrder.TABLE_PRICE * 2 + DinetteOrder.CHAIR_PRICE * 6 + DinetteOrder.LEAF_PRICE * 3, testDinetteStore.getTotalSales(), .01);
        assertEquals((DinetteOrder.TABLE_PRICE * 2 + DinetteOrder.CHAIR_PRICE * 6 + DinetteOrder.LEAF_PRICE * 3)/2, testDinetteStore.getAvgOrderPrice(), .01);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testPreconConstructorTableInventory() {
        DinetteStore testDinetteStore = new DinetteStore(-1,10,10);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testPreconConstructorChairInventory() {
        DinetteStore testDinetteStore = new DinetteStore(10,-1,10);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testPreconConstructorLeafInventory() {
        DinetteStore testDinetteStore = new DinetteStore(10,10,-1);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testPreconTableInventory() {
        DinetteStore testDinetteStore = new DinetteStore(0,10,10);
        DinetteOrder testOrder = new DinetteOrder(1);
        testDinetteStore.submitOrder(testOrder);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testPreconChairInventory() {
        DinetteStore testDinetteStore = new DinetteStore(10,0,10);
        DinetteOrder testOrder = new DinetteOrder(1, 1, 1, DinetteOrder.Option.CLEANING_KIT);
        testDinetteStore.submitOrder(testOrder);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testPreconLeafInventory() {
        DinetteStore testDinetteStore = new DinetteStore(10,10,0);
        DinetteOrder testOrder = new DinetteOrder(1, 1, 1, DinetteOrder.Option.CLEANING_KIT);
        testDinetteStore.submitOrder(testOrder);
    }
}
