    /**
 * DinetteOrder class for specifying a dinette order comprised of tables, chairs, leaves, and a free item
 * 
 * @author      Nathaniel Wieck
 */
public class DinetteOrder {
    
    public static enum Option {CLEANING_KIT, SEAT_CUSHIONS, PADDED_FEET};
    
    public static final double TABLE_PRICE = 279;
    public static final double CHAIR_PRICE = 69;
    public static final double LEAF_PRICE = 45;
                                                                                                                                                                                                                                                                                                
    private int orderNumber;
    private int chairCount;
    private int leafCount;
    private Option option;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
    private double price;
    
    /**
     * Default constructor
     */
    public DinetteOrder(int orderNumber) {
        this(orderNumber, 4, 0, Option.CLEANING_KIT);
    }
    
    /**
     * Full constructor (
     */
    public DinetteOrder(int orderNumber, int chairCount, int leafCount, Option option) {
        this.orderNumber = orderNumber;
        this.setChairCount(chairCount);
        this.setLeafCount(leafCount); 
        this.option = option;
    }
    
    /**
     * Retrieves order number
     * 
     * @return      order number
     */
    public int getOrderNumber() {
        return orderNumber;
    }
    
    /**
     * Retrieves chair count in order
     * 
     * @return      chair count
     */
    public int getChairCount() {
        return chairCount;
    }
    
     /**
     * Retrieves leaf count in order
     * 
     * @return      leaf count
     */
    public int getLeafCount() {
        return leafCount;
    }
    
     /**
     * Retrieves option for free item in order
     * 
     * @return      option
     */
    public Option getOption() {
        return option;
    }
    
     /**
     * Retrieves total price of the order
     * 
     * @return      order price
     */
    public double getPrice() {
        price = TABLE_PRICE + (chairCount * CHAIR_PRICE) + (leafCount * LEAF_PRICE);
        
        return price;
    }
    
     /**
     * Assigns chair count for the order
     * 
     * @param   chairCount  the desired count of chairs
     */
    public void setChairCount(int chairCount) {
        if (chairCount < 0 || chairCount > 10) {
            throw new IllegalArgumentException("Invalid order: must order between 0 and 10 chairs.");
        }
        this.chairCount = chairCount;
    }
    
    /**
     * Assigns leaf count for the order
     * 
     * @param   leafCount  the desired count of leaves
     */
    public void setLeafCount(int leafCount) {
        if (leafCount < 0 || leafCount > 2) {
            throw new IllegalArgumentException("Invalid order: must order between 0 and 2 leaves.");
        }
        this.leafCount = leafCount;
    }
    
    /**
     * Assigns free item option for the order
     * 
     * @param   option  the desired free item option
     */
    public void setOption(Option option) {
        if (option == null) {
            throw new IllegalArgumentException("Free item option must not be null.");
        }
        this.option = option;
    }

    /**
     * Creates a text description for this order
     *
     * @return     a string describing the order
     */
    public String toString() {
        String descrip = "Order Details\n<><>\n";
        descrip += "Order Number: " + orderNumber + "\n";
        descrip += "Chair Count: " + chairCount + "\n";
        descrip += "Leaf Count: " + leafCount + "\n";
        descrip += "Free Item Option: " + option + "\n";
        descrip += "Sales Total: $" + String.format("%.2f",getPrice()) + "\n";
        
        return descrip;
    }
}