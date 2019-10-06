import java.util.Arrays;

/** WindowCoverings class super-superclass specifies broad fields that apply to all related types of window coverings.
 *  @author     Nathaniel Wieck
 *  @version    7 MAR 2019
 */ 
public abstract class WindowCoverings {

    public static enum Ops {RAISE, LOWER, TILT, SLIDE};
    
    private double width;
    private double height;
    private double rValue;
    private Ops[] ops;

    /**
     * Create WindowCoverings object
     * @param width double
     * @param height double
     * @param rValue double
     * @param ops array of enumerated Ops, window operations
     */
    public WindowCoverings (double width, double height, double rValue, Ops[] ops) {
        if (width < 0.0 || height < 0.0) {
            throw new IllegalArgumentException("Width or height cannot be negative");
        }
        this.width = width;
        this.height = height;
        this.rValue = rValue;
        this.ops = ops;
    }
    
    /**
     * Get width of window covering in inches
     * @return width in inches
     */
    public double getWidth() {
        return width;
    }
    
    /**
     * Get height of window covering in inches
     * @return height in inches
     */
    public double getHeight() {
        return height;
    }
        
    /**
     * Get R-Value of window covering
     * @return R-Value
     */
    public double getRValue() {
        return rValue;
    }
    
    /**
     * Get operations of window covering
     * @return array of enumerated window covering operations
     */
    public Ops[] getOps() {
        return ops;
    }
    
    /**
     * Abstract method to get header price in subclasses
     */
    abstract double getPriceHead();
    
    /**
     * Abstract method to get material price in subclasses
     */
    abstract double getPriceMat();
    
    /**
     * Get total price of window covering - if only width value provided and no length value, assumed only calculating cost of header
     * @return total price of window covering
     */
    public double getPriceTotal() {
        return getPriceHead() + getPriceMat();
    }
    
    /**
     * The String version of this LineSet
     * @return the String representation
     */
    public String toString() {
        String descrip = "";    
        descrip += String.format("Total Price: $%-10.2f", getPriceTotal());
        descrip += String.format("Width: %-10.3f", getWidth());
        descrip += String.format("Height: %-10.3f", getHeight());
        descrip += String.format("R-Value: %-6.1f", getRValue());
        descrip += "Operations: " + Arrays.toString(getOps()) + "\t";
        
        return descrip;
    }
    
    /**
     * Compare two WindowCoverings objects to test for state equality
     * @param coal Object
     * @return true or false if compared object contents are equal or not
     */
    public boolean equals(Object coal) {
        WindowCoverings diamond = (WindowCoverings) coal;
        boolean result = false;
        if (width == diamond.getWidth() && height == diamond.getHeight() && rValue == diamond.getRValue()) {
            for (int i = 0; i < diamond.getOps().length; i++) {
                if (ops[i] != diamond.getOps()[i]) {
                    break;
                }
            }
            result = true;
        }
        return result;
    }
}
