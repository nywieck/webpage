/** Honeycomb class of shades type window coverings extends WindowCoverings, cords and sides thereof are specific to this subclass of WindowCoverings
 *  @author     Nathaniel Wieck
 *  @version    7 MAR 2019
 */ 
public abstract class Honeycomb extends WindowCoverings {

    public static enum Cord {PULL, CONTINUOUS};
    public static enum Side {LEFT, RIGHT};
    
    private Cord cord;
    private Side side;

    /**
     * Create Honeycomb object, calls WindowCoverings constructor
     * @param width double
     * @param height double
     * @param rValue double
     * @param cord Cord
     * @param side Side
     */ 
    public Honeycomb (double width, double height, double rValue, Cord cord, Side side) {
        super(width, height, rValue, new WindowCoverings.Ops[]{WindowCoverings.Ops.RAISE, WindowCoverings.Ops.LOWER,});
        this.cord = cord;
        this.side = side;
    }
    
    /**
     * Get cord type of window covering
     * @return cord type
     */
    public Cord getCord() {
        return cord;
    }
    
    /**
     * Get cord side for window covering
     * @return cord side for window covering
     */
    public Side getSide() {
        return side;
    }
    
    /**
     * Abstract method to get specific window covering header price in subclasses
     */
    abstract double getPriceHeadSpec();
    
    /**
     * Get price of window covering header in $ / foot
     * @return price of window covering header in $ / foot
     */
    public double getPriceHead() {
        return getPriceHeadSpec();
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
        descrip += String.format("Side: %-9s", getSide());
        descrip += String.format("Cord: %-12s", getCord());

        return descrip;
    }
    
    /**
     * Compare two Honeycomb objects to test for state equality
     * @param coal Object
     * @return true or false if compared object contents are equal or not
     */
    public boolean equals(Object coal) {
        boolean result = super.equals(coal);
        if (result) {
            Honeycomb diamond = (Honeycomb) coal;
            if (cord != diamond.getCord() || side != diamond.getSide()) {
                result = false;
            }
        } 
        
        return result;
    }
}
