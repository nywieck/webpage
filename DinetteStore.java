/**
 * DinetteStore class for specifying and updating dinette inventory and
 * logistics, tracking financial calculations, and submitting orders
 * 
 * @author      Nathaniel Wieck
 */
public class DinetteStore {
    
    private int     tableInventory;
    private int     chairInventory;
    private int     leafInventory;
    private int     tablesOnOrder;
    private int     chairsOnOrder;
    private int     leavesOnOrder;
    private double  totalSales;
    private int     totalOrders;
    private double  avgOrderPrice;

    /**
     * Constructor 
     */
    public DinetteStore(int tableInventory, int chairInventory, int leafInventory) {   
        this.tableInventory = tableInventory;
        if (tableInventory < 0) {
            throw new IllegalArgumentException("Insufficient table inventory.");
        }
        
        this.chairInventory = chairInventory;
        if (chairInventory < 0) {
            throw new IllegalArgumentException("Insufficient chair inventory.");
        }
        
        this.leafInventory = leafInventory;
        if (leafInventory < 0) {
            throw new IllegalArgumentException("Insufficient leaf inventory.");
        }
        
        tablesOnOrder = 0;
        chairsOnOrder = 0;
        leavesOnOrder = 0;
        totalSales = 0;
        totalOrders = 0;
        avgOrderPrice = 0;
    }
    
    /**
     * Retrieves the current table inventory
     *
     * @return     current table inventory
     */
    public int getTableInventory() {
        return tableInventory;
    }
        
    /**
     * Retrieves the current chair inventory
     *
     * @return     current chair inventory
     */
    public int getChairInventory() {
        return chairInventory;
    }
    
    /**
     * Retrieves the current leaf inventory
     *
     * @return     current leaf inventory
     */
    public int getLeafInventory() {
        return leafInventory;
    }
    
    /**
     * Retrieves the current number of tables on order
     *
     * @return     current number of tables on order
     */
    public int getTablesOnOrder() {
        return tablesOnOrder;
    }
    
    /**
     * Retrieves the current number of chairs on order
     *
     * @return     current number of chairs on order
     */
    public int getChairsOnOrder() {
        return chairsOnOrder;
    }
    
    /**
     * Retrieves the current number of leaves on order
     *
     * @return     current number of leaves on order
     */
    public int getLeavesOnOrder() {
        return leavesOnOrder;
    }
    
    /**
     * Retrieves the total sales
     *
     * @return     total sales
     */
    public double getTotalSales() {
        return totalSales;
    }
    
    /**
     * Retrieves the average order price
     *
     * @return     average order price
     */
    public double getAvgOrderPrice() {
        
        if (totalOrders == 0) {
            avgOrderPrice = 0;
        }
        else {
            avgOrderPrice = totalSales / totalOrders;
        }
        
        return avgOrderPrice;
    }
    
     /**
     * Submits an order, updates inventories, running totals, and financial calculations
     * 
     * @return      order price
     */
    public double submitOrder(DinetteOrder order){
        int chairCount = order.getChairCount();
        int leafCount = order.getLeafCount();
        tableInventory -= 1;
        chairInventory -= chairCount;
        leafInventory -= leafCount;
        tablesOnOrder += 1;
        chairsOnOrder += chairCount;
        leavesOnOrder += leafCount;
        double price = order.getPrice();
        totalSales += price;
        totalOrders += 1;
        
        if (tableInventory < 0) {
            throw new IllegalArgumentException("Insufficient table inventory to complete order.");
        }
        
        if (chairInventory < 0) {
            throw new IllegalArgumentException("Insufficient chair inventory to complete order.");
        }
        
        if (leafInventory < 0) {
            throw new IllegalArgumentException("Insufficient leaf inventory to complete order.");
        }
        
        return price;
    }
    
    /**
     * Creates a text description for current logistical details
     *
     * @return     a string describing the logistical details
     */
    public String toString() {   
        String descrip = "Real-time logistics report\n<><>\n";
        descrip += "Table Inventory: " + tableInventory + "\n";
        descrip += "Chair Inventory: " + chairInventory + "\n";
        descrip += "Leaf Inventory: " + leafInventory + "\n";
        descrip += "Tables on Order: " + tablesOnOrder + "\n";
        descrip += "Chairs on Order: " + chairsOnOrder + "\n";
        descrip += "Leaves on Order: " + leavesOnOrder + "\n";
        descrip += "Total Sales: $" + String.format("%.2f",getTotalSales()) + "\n";
        descrip += "Average Order Price: $" + String.format("%.2f",getAvgOrderPrice()) + "\n";
        
        return descrip;
    }
}