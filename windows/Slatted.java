/** Slatted class of blinds type window coverings extends WindowCoverings, depth is specific to this subclass of WindowCoverings
 *  @author     Nathaniel Wieck
 *  @version    7 MAR 2019
 */ 
public abstract class Slatted extends WindowCoverings {
    
    private int depth;
    
    /**
     * Create Slatted object, calls WindowCoverings constructor
     * @param width double
     * @param height double
     * @param rValue double
     * @param depth int
     */
    public Slatted (double width, double height, double rValue, int depth) {
        super(width, height, rValue, new WindowCoverings.Ops[]{WindowCoverings.Ops.RAISE, WindowCoverings.Ops.LOWER, WindowCoverings.Ops.TILT});
        this.depth = depth;
    }

    /**
     * Get depth of window covering in inches
     * @return depth in inches
     */
    public int getDepth() {
        return depth;
    }
    
    /**
     * Get price of window covering header in $ / foot
     * @return price of window covering header in $ / foot
     */
    public double getPriceHead() {
        return 20.0;
    }
    
    /**
     * Abstract method to get specific window covering materials price in subclasses
     */
    abstract double getPriceMatSpec();

    /**
     * Get price of window covering materials in $ / square foot
     * @return price of window covering materials in $ / square foot
     */
    public double getPriceMat() {
        return getPriceMatSpec();
    }
    
    /**
     * The String version of this LineSet
     * @return the String representation
     */
    public String toString() {
        String descrip = super.toString();
        descrip +=  "Depth: " + getDepth() + "\"";

        return descrip;
    }
    
    /**
     * Compare two Slatted objects to test for state equality
     * @param coal Object
     * @return true or false if compared object contents are equal or not
     */
    public boolean equals(Object coal) {
        boolean result = super.equals(coal);
        if (result) {
            Slatted diamond = (Slatted) coal;
            if (depth != diamond.getDepth()) {
                result = false;
            }
        }

        return result;
    }
}
