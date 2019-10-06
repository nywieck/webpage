/** None class of curtains extends Curtains
 *  @author     Nathaniel Wieck
 *  @version    7 MAR 2019
 */ 
public class None extends Curtains {
    
    /**
     * Create None object, calls Curtains constructor
     * @param width double
     * @param height double
     */ 
    public None (double width, double height) {
        super(width, height, 1.1, Curtains.Lining.NONE);
    }
    
    /**
     * Get price of window covering materials in $ / square foot
     * @return price of window covering materials in $ / square foot
     */
    public double getPriceMat() {
        return (getWidth() / 12) * (getHeight() / 12) * super.getPriceMat();
    }
    
    /**
     * The String version of this LineSet
     * @return the String representation
     */
    public String toString() {
        String descrip = "< NO-LINING Curtains > \t";
        descrip += super.toString();
        
        return descrip;
    }
}
