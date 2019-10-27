/** Book class implements Quotable and Comparable interfaces, creates a new Book object
 *  @author     Nathaniel Wieck
 *  @version    13 MAR 2019
 */ 
public class Book implements Quotable, Comparable <Book> {
    
    private String isbn;
    private String author;
    private String title;
    private String publisher;
    private int pubYear;
    private int pubMonth;
    private int pubDay;
    private int pageCount;
    
    /**
     * Create Book object
     * @param isbn string of book ISBN
     * @param author string of book author
     * @param title string of book title
     * @param publisher string of book publisher
     * @param pubYear integer of year book published
     * @param pubMonth integer of month book published
     * @param pubDay integer of day book published
     * @param pageCount integer of number of pages in book
     */
    public Book (String isbn, String author, String title, String publisher, int pubYear, int pubMonth, int pubDay, int pageCount) {
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.publisher = publisher;
        this.pubYear = pubYear;
        this.pubMonth = pubMonth;
        this.pubDay = pubDay;
        this.pageCount = pageCount;
    }
    
    /**
     * Get book ISBN as string
     * @return book isbn
     */
    public String getIsbn() {
        return isbn;
    }
    
    /**
     * Get book author as string
     * @return book author
     */
    public String getAuthor() {
        return author;
    }
    
    /**
     * Get book title as string
     * @return book title
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Get book publisher as string
     * @return book publisher
     */
    public String getPublisher() {
        return publisher;
    }
    
    /**
     * Get year book published as integer
     * @return year book published
     */
    public int getPubYear() {
        return pubYear;
    }
    
    /**
     * Get month book published as integer
     * @return month book published
     */
    public int getPubMonth() {
        return pubMonth;
    }
    
    /**
     * Get day book published as integer
     * @return day book published
     */
    public int getPubDay() {
        return pubDay;
    }
    
    /**
     * Get number of pages in book as integer
     * @return number of pages in book
     */
    public int getPageCount() {
        return pageCount;
    }
    
    /**
     * Compare isbn between two Book objects for purposes of sorting
     * @param otherBook second Book object
     * @return integer that is positive, negative or 0
     */
    public int compareTo(Book otherBook) {
        return isbn.compareTo(otherBook.getIsbn());
    }
    
    /**
     * The String version of Book
     * @return the String representation
     */
    public String toString() {
        String descrip = "";    
        descrip += "Book ISBN:\t\t" + getIsbn() + "\n"; 
        descrip += "Title:\t\t\t" + getTitle() + "\n";
        descrip += "Author:\t\t\t" + getAuthor() + "\n";
        descrip += "Publisher:\t\t" + getPublisher() + "\n";
        descrip += "Publication Date:\t" + getPubMonth() + "/" + getPubDay() + "/" + getPubYear() + "\n";
        descrip += "Page count:\t\t" + getPageCount() + "\n\n"; 
        
        return descrip;
    }
}
