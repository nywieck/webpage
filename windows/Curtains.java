/** Curtains class of curtains type window coverings extends WindowCoverings, lining type is specific to this subclass of WindowCoverings
 *  @author     Nathaniel Wieck
 *  @version    7 MAR 2019
 */ 
public abstract class Curtains extends WindowCoverings {

    public static enum Lining {NONE, LIGHT, NORMAL, HEAVY};
    
    private Lining lining;
    
    /**
     * Create Curtains object, calls WindowCoverings constructor
     * @param width double
     * @param height double
     * @param rValue double
     * @param lining Lining
     */ 
    public Curtains (double width, double height, double rValue, Curtains.Lining lining) {
        super(width, height, rValue, new WindowCoverings.Ops[]{WindowCoverings.Ops.SLIDE});
        this.lining = lining;
    }
    
    /**
     * Get type of lining of window covering
     * @return type of lining of window covering
     */
    public Lining getLining() {
        return lining;
    }
    
    /**
     * Get price of window covering header in $ / foot
     * @return price of window covering header in $ / foot
     */
    public double getPriceHead() {
        return 9.0 * (getWidth() / 12);
    }
    
    /**
     * Get price of window covering materials in $ / square foot
     * @return price of window covering materials in $ / square foot
     */
    public double getPriceMat() {
        return 7.0;
    }
    
    /**
     * The String version of this LineSet
     * @return the String representation
     */
    public String toString() {
        String descrip = super.toString();
        descrip += String.format("Lining: %-9s", getLining());

        return descrip;
    }
    
    /**
     * Compare two Curtains objects to test for state equality
     * @param coal Object
     * @return true or false if compared object contents are equal or not
     */
    public boolean equals(Object coal) {
        boolean result = super.equals(coal);
        if (result) {
            Curtains diamond = (Curtains) coal;
            if (lining != diamond.getLining()) {
                result = false;
            }
        }
    
        return result;
    }
}
