/** Plastic class of blinds extends Slatted
 *  @author     Nathaniel Wieck
 *  @version    7 MAR 2019
 */ 
public class Plastic extends Slatted {
    
    /**
     * Create Plastic object, calls Slatted constructor
     * @param width double
     * @param height double
     */ 
    public Plastic (double width, double height) {
        super(width, height, 1.8, 2);
    }

    /**
     * Get price of window covering header in $ / foot
     * @return price of window covering header in $ / foot
     */
    public double getPriceHead() {
        return super.getPriceHead() * (getWidth() / 12);
    }
    
    /**
     * Get price of window covering materials in $ / square foot
     * @return price of window covering materials in $ / square foot
     */
    public double getPriceMatSpec() {
        return (getWidth() / 12) * (getHeight() / 12) * 10.0;
    }
    
    /**
     * The String version of this LineSet
     * @return the String representation
     */
    public String toString() {
        String descrip = "< PLASTIC Blinds > \t";
        descrip += super.toString();
        
        return descrip;
    }
}