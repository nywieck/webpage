/** Single class of shades extends Honeycomb
 *  @author     Nathaniel Wieck
 *  @version    7 MAR 2019
 */ 
public class Single extends Honeycomb {
    
    /**
     * Create Single object, calls Honeycomb constructor
     * @param width double
     * @param height double
     * @param cord Cord
     * @param side Side
     */ 
    public Single (double width, double height, Honeycomb.Cord cord, Honeycomb.Side side) {
        super(width, height, 2.2, cord, side);
    }
    
    /**
     * Get price of window covering header in $ / foot, depending on cord type
     * @return price of window covering header in $ / foot
     */
    public double getPriceHeadSpec() {
        double priceHeadSpec;
        if (getCord() == Honeycomb.Cord.PULL) {
            priceHeadSpec = (16.0) * (getWidth() / 12);
        } else {
            priceHeadSpec = (17.0) * (getWidth() / 12);
        }
        
        return priceHeadSpec;
    }
    
    /**
     * Get price of window covering materials in $ / square foot
     * @return price of window covering materials in $ / square foot
     */
    public double getPriceMatSpec() {
        return (getWidth() / 12) * (getHeight() / 12) * 12.0;
    }
    
    /**
     * The String version of this LineSet
     * @return the String representation
     */
    public String toString() {
        String descrip = "< SINGLE Shades > \t";
        descrip += super.toString();
        
        return descrip;
    }
}
