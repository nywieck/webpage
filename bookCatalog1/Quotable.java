/** Quotable interface with abstract methods that can be implemented for Book objects
 *  @author     Nathaniel Wieck
 *  @version    13 MAR 2019
 */ 
public interface Quotable {
    public String getIsbn();
    public String getAuthor();
    public String getTitle();
    public String getPublisher();
    public int getPubYear();
    public int getPubMonth();
    public int getPubDay();
    public int getPageCount();
}