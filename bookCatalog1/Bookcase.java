import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.io.PrintStream;

/** Bookcase class creates a Bookcase object that creates and interacts with an ArrayList of Book objects
 *  @author     Nathaniel Wieck
 *  @version    13 MAR 2019
 */ 
public class Bookcase {
    
    private ArrayList<Book> arrayOfBooks;
    private String fileName;
    
    /**
     * Create Bookcase object that creates an ArrayList and calls readFile() method
     * @param fileName name of file containing data for the library
     */
    public Bookcase(String fileName) throws FileNotFoundException {
        this.arrayOfBooks = new ArrayList<Book>();
        this.fileName = fileName;
        readFile();
    }
    
    /**
     * Read the input file with data for the library and completes the ArrayList by filling it with Book objects from the file
     */
    public void readFile() throws FileNotFoundException {
        File file = new File(fileName);
        Scanner input = new Scanner(file);
        while (input.hasNext()) {
            arrayOfBooks.add(new Book(input.nextLine(), input.nextLine(), input.nextLine(), input.nextLine(), input.nextInt(), input.nextInt(), input.nextInt(), input.nextInt()));
            input.nextLine();
        }
        input.close(); 
        sortBooks();
    }

    /**
     * Write updated file with any accepted changes made by user
     */
    public void writeFile() throws FileNotFoundException {
        File file = new File(fileName);
        PrintStream output = new PrintStream(file);
        for (int i = 0; i < bookCount(); i++) {
            output.print(bookAt(i).getIsbn() + "\n" + bookAt(i).getAuthor() + "\n" +
                         bookAt(i).getTitle() + "\n" + bookAt(i).getPublisher() + "\n" +
                         bookAt(i).getPubYear() + " " + bookAt(i).getPubMonth() + " " +
                         bookAt(i).getPubDay() + "\n" + bookAt(i).getPageCount() + "\n");
        }
        output.close();
    }
    
    /**
     * Get book count of Book objects in Bookcase ArrayList as integer
     * @return book count
     */
    public int bookCount() {
        return arrayOfBooks.size();
    }
    
    /**
     * Get Book object at a specified index in the ArrayList
     * @param i index of ArrayList
     * @return Book object at specified index of ArrayList
     */
    public Book bookAt(int i) {
        return arrayOfBooks.get(i);
    }
    
    /**
     * Get index of Book object in ArrayList as an integer, if exists
     * @param isbn book's isbn as a string
     * @return index of specified Book object in ArrayList, or -1 if does not exist
     */
    public int search(String isbn) {
        int result = -1;
        for (int i = 0; i < bookCount(); i++) {
            if (isbn.equals(bookAt(i).getIsbn())) {
                result = i;
                break;
            }
        }
        
        return result;
    }
    
    /**
     * Add new Book object to the Bookcase, as specified by user input
     * @param newBook new Book object that is being added to the Bookcase
     */
    public void add(Book newBook){
        arrayOfBooks.add(newBook);
        sortBooks();
    }
    
    /**
     * Delete a Book object from the Bookcase, as specified by user input
     * @param isbn book's isbn as a string
     * @return confirmation that book was deleted or not found, as a string
     */
    public String delete(String isbn) {
        int index = search(isbn);
        String result = "";
        if (index < 0) {
            result = "Book is not on the bookshelf.";
        } else {
            String title = bookAt(index).getTitle();
            arrayOfBooks.remove(index);
            result = "\n" + title + " successfully removed, thank you.\n";
        }
        
        return result;
    }
    
    /**
     * Sort Book objects in ArrayList in order by isbn
     */
    public void sortBooks() {
        Collections.sort(arrayOfBooks);
    }

    /**
     * Clears all Book objects from ArrayList
     */
    public void clearBooks()  throws FileNotFoundException {
        arrayOfBooks.clear();
    }
        
    /**
     * Get file name
     * @return input file name as a string
     */
    public String getFileName() {
        return fileName;
    }
    
    /**
     * The String version of Bookcase
     * @return the String representation
     */
    public String toString() {
        String descrip = "";
        descrip += "<< SUMMARY OF BOOKS ON THE BOOKSHELF >>" + "\n";
        descrip += "Total books on the bookshelf: " + bookCount() + "\n\n";
        for (Book book: arrayOfBooks) {
            descrip += book.toString();
        }
        
        return descrip;
    }
}