/** Light class of curtains extends Curtains
 *  @author     Nathaniel Wieck
 *  @version    7 MAR 2019
 */ 
public class Light extends Curtains {
    
    /**
     * Create Light object, calls Curtains constructor
     * @param width double
     * @param height double
     */ 
    public Light (double width, double height) {
        super(width, height, 1.5, Curtains.Lining.LIGHT);
    }
    
    /**
     * Get price of window covering materials in $ / square foot
     * @return price of window covering materials in $ / square foot
     */
    public double getPriceMat() {
        return (getWidth() / 12) * (getHeight() / 12) * (super.getPriceMat() + 2.0);
    }
    
    /**
     * The String version of this LineSet
     * @return the String representation
     */
    public String toString() {
        String descrip = "< LIGHT Curtains > \t";
        descrip += super.toString();
        
        return descrip;
    }
}
