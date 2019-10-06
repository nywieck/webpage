/** Heavy class of curtains extends Curtains
 *  @author     Nathaniel Wieck
 *  @version    7 MAR 2019
 */ 
public class Heavy extends Curtains
{   
    /**
     * Create Heavy object, calls Curtains constructor
     * @param width double
     * @param height double
     */ 
    public Heavy (double width, double height) {
        super(width, height, 2.1, Curtains.Lining.HEAVY);
    }
        
    /**
     * Get price of window covering header in $ / foot
     * @return price of window covering header in $ / foot
     */
    public double getPriceHead() {
        return 12.0 * (getWidth() / 12);
    }
    
    /**
     * Get price of window covering materials in $ / square foot
     * @return price of window covering materials in $ / square foot
     */
    public double getPriceMat() {
        return (getWidth() / 12) * (getHeight() / 12) * (super.getPriceMat() +4.0);
    }
    
    /**
     * The String version of this LineSet
     * @return the String representation
     */
    public String toString() {
        String descrip = "< HEAVY Curtains > \t";
        descrip += super.toString();
        
        return descrip;
    }
}
