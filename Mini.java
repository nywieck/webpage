/** Mini class of blinds extends Slatted
 *  @author     Nathaniel Wieck
 *  @version    7 MAR 2019
 */ 
public class Mini extends Slatted {
    
    /**
     * Create Mini object, calls Slatted constructor
     * @param width double
     * @param height double
     */ 
    public Mini (double width, double height) {
        super(width, height, 1.4, 1);
    }

    /**
     * Get price of window covering header in $ / foot
     * @return price of window covering header in $ / foot
     */
    public double getPriceHead() {
        return (super.getPriceHead() + 1) * (getWidth() / 12);
    }
    
    /**
     * Get price of window covering materials in $ / square foot
     * @return price of window covering materials in $ / square foot
     */
    public double getPriceMatSpec() {
        return (getWidth() / 12) * (getHeight() / 12) * 13.0;
    }
    
    /**
     * The String version of this LineSet
     * @return the String representation
     */
    public String toString() {
        String descrip = "< MINI Blinds > \t";
        descrip += super.toString();
            
        return descrip;
    }
}