/** Double class of shades extends Honeycomb
 *  @author     Nathaniel Wieck
 *  @version    7 MAR 2019
 */ 
public class Double extends Honeycomb {

    /**
     * Create Double object, calls Honeycomb constructor
     * @param width double
     * @param height double
     * @param cord Cord
     * @param side Side
     */ 
    public Double (double width, double height, Honeycomb.Cord cord, Honeycomb.Side side) {
        super(width, height, 2.7, cord, side);
    }
    
    /**
     * Create Double object with different signature for Blackout subclass, calls Honeycomb constructor
     * @param width double
     * @param height double
     * @param rValue double
     * @param cord Cord
     * @param side Side
     */ 
    public Double (double width, double height, double rValue, Honeycomb.Cord cord, Honeycomb.Side side) {
        super(width, height, rValue, cord, side);
    }
    
    /**
     * Get price of window covering header in $ / foot, depending on cord type
     * @return price of window covering header in $ / foot
     */
    public double getPriceHeadSpec() {
        double priceHeadSpec;
        if (getCord() == Honeycomb.Cord.PULL) {
            priceHeadSpec = (19.0) * (getWidth() / 12);
        } else {
            priceHeadSpec = (21.0) * (getWidth() / 12);
        }
        
        return priceHeadSpec;
    }
    
    /**
     * Get price of window covering materials in $ / square foot
     * @return price of window covering materials in $ / square foot
     */
    public double getPriceMatSpec() {
        return (getWidth() / 12) * (getHeight() / 12) * 15.0;
    }
    
    /**
     * The String version of this LineSet
     * @return the String representation
     */
    public String toString() {
        String descrip = "< DOUBLE Shades > \t";
        descrip += super.toString();
        
        return descrip;
    }
}