/** Main class to printout examples of toStrings for many various window coverings
 *  @author     Nathaniel Wieck
 *  @version    7 MAR 2019
 */ 
public class Main {
    /**
     * Main creates array of WindowCovering objects and prints their toStrings
     */
    public static void main (String args[]) {
        WindowCoverings[] testArray = new WindowCoverings[20];
        testArray[0] = new Wood(10.0, 10.0);
        testArray[1] = new Wood(98.625, 0.0);
        testArray[2] = new Plastic(20.0, 5.0);
        testArray[3] = new Plastic(0.0, 47.125);
        testArray[4] = new Mini(0.125, 50.0);
        testArray[5] = new Mini(0.0, 0.0);
        testArray[6] = new Single(10.0, 10.0, Honeycomb.Cord.PULL, Honeycomb.Side.RIGHT);
        testArray[7] = new Single(10.0, 10.0, Honeycomb.Cord.CONTINUOUS, Honeycomb.Side.RIGHT);
        testArray[8] = new Double(10.0, 10.0, Honeycomb.Cord.CONTINUOUS, Honeycomb.Side.LEFT);
        testArray[9] = new Double(142.75, 13.25, Honeycomb.Cord.PULL, Honeycomb.Side.RIGHT);
        testArray[10] = new Blackout(20.0, 5.0, Honeycomb.Cord.CONTINUOUS, Honeycomb.Side.RIGHT);
        testArray[11] = new Blackout(20.0, 5.0, Honeycomb.Cord.PULL, Honeycomb.Side.RIGHT);
        testArray[12] = new None(20.0, 5.0);
        testArray[13] = new None(0.0, 14.9);  
        testArray[14] = new Light(0.0, 50.0);
        testArray[15] = new Light(15.75, 250.125);
        testArray[16] = new Normal(50.0, 0.0);
        testArray[17] = new Normal(15.75, 250.125); 
        testArray[18] = new Heavy(10.0, 10.0);
        testArray[19] = new Heavy(0.0, 150.5);
        for (int i = 0; i < testArray.length; i++) {
            System.out.println(testArray[i].toString() + "\n");
        }
    }
}
