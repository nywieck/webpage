/** Normal class of curtains extends Curtains
 *  @author     Nathaniel Wieck
 *  @version    7 MAR 2019
 */ 
public class Normal extends Curtains {
    
    /**
     * Create Normal object, calls Curtains constructor
     * @param width double
     * @param height double
     */ 
    public Normal (double width, double height) {
        super(width, height, 1.8, Curtains.Lining.NORMAL);
    }
    
    /**
     * Get price of window covering materials in $ / square foot
     * @return price of window covering materials in $ / square foot
     */
    public double getPriceMat() {
        return (getWidth() / 12) * (getHeight() / 12) * (super.getPriceMat() + 3.0);
    }
    
    /**
     * The String version of this LineSet
     * @return the String representation
     */
    public String toString() {
        String descrip = "< NORMAL Curtains > \t";
        descrip += super.toString();
        
        return descrip;
    }
}
