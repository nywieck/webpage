/** Blackout class of shades extends Honeycomb
 *  @author     Nathaniel Wieck
 *  @version    7 MAR 2019
 */ 
public class Blackout extends Double {
    
    /**
     * Create Blackout object, calls Double constructor
     * @param width double
     * @param height double
     * @param cord Cord
     * @param side Side
     */ 
    public Blackout (double width, double height, Honeycomb.Cord cord, Honeycomb.Side side) {
        super(width, height, 3.9, cord, side);
    }
    
    /**
     * Get price of window covering materials in $ / square foot
     * @return price of window covering materials in $ / square foot
     */
    public double getPriceMatSpec() {
        return (getWidth() / 12) * (getHeight() / 12) * 16.0;
    }
    
    /**
     * Get R-Value of window covering
     * @return R-Value of window covering
     */
    public double getRValue() {
        return 3.9;
    }
    
    /**
     * The String version of this LineSet
     * @return the String representation
     */
    public String toString() {
        String descrip = "*BLACKOUT ";
        descrip += super.toString();
        
        return descrip;
    }
}