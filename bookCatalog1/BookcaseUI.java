import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.Long;

/** BookcaseUI class interacts with user for browsing and modifying library file, instantiates Bookcase object
 *  @author     Nathaniel Wieck
 *  @version    13 MAR 2019
 */ 
public class BookcaseUI {        
    /**
     * Main instantiates Bookcase using input library file, loops 4 choice option menu that calls other methods to interact with user to browse and modify file
     */
    public static void main (String args[]) throws FileNotFoundException {
        Bookcase bookcase = new Bookcase("Library.txt");
        Scanner input = new Scanner(System.in);
        int choice = 0;
        while (choice != 4) {
            System.out.println("Enter a number corresponding to one of the following options:\n");
            System.out.println("1. Display all library books");
            System.out.println("2. Add a book");
            System.out.println("3. Delete a book");
            System.out.println("4. Exit the program");
            choice = getInRangeInt(1, 4);
            switch (choice) {
                case 1: 
                display(bookcase);
                break;
                
                case 2:
                add(bookcase);
                break;
                
                case 3: 
                delete(bookcase);
                break;
                
                case 4:
                exit(bookcase);
                break;
            }
        }
        input.close();
    }

    /**
     * Validate user input data type and range for integers (must be integer within specified range), prevents bad user input to maintain integrity of library file
     * @param min integer representing lowest acceptable value
     * @param max integer representing highest acceptable value
     * @return accepted integer that has been validated
     */
    public static int getInRangeInt(int min, int max) {
        Scanner input = new Scanner(System.in);
        int result = 0;
        boolean flag = true;
        while(flag) {
            if(input.hasNextInt()) {
                result = input.nextInt();
                if (result >= min && result <= max) {
                    flag = false;
                } else {
                    System.out.println("Invalid entry - must enter an integer between " + min + " and " + max + ", try again.");
                }
            } else {
                System.out.println("Invalid entry - must enter an integer between " + min + " and " + max + ", try again.");
                input.next();
            }
        }
        input.close();
        
        return result;
    }
    
    /**
     * Validate user input for String data (must not be null or empty), prevents bad user input to maintain integrity of library file
     * @return accepted String that has been validated
     */
    public static String getValidString() {
        Scanner input = new Scanner(System.in);
        String result = input.nextLine();
        String temp = result.replace(" ", "");
        while(result == null || temp.equals("")) {
            System.out.println("Invalid entry - must not be null or empty, try again.");
            result = input.nextLine();
            temp = result.replace(" ","");
        }
        input.close();
        
        return result;
    }
    
    /**
     * Validate user input for book ISBN String (must be exactly 13-digits), prevents bad user input to maintain integrity of library file
     * @return accepted String that has been validated
     */
    public static String getValidIsbn() {
        Scanner input = new Scanner(System.in);
        String result = getValidString();
        boolean flag = true;
        while(flag) {
            if (result.length() == 13) {
                try { 
                    Long.parseLong(result);
                    flag = false;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid entry - must be a number, try again.");
                    result = getValidString();
                }
            } else {
                System.out.println("Invalid entry - must be 13-digits, try again.");
                result = getValidString();
            }
        }
        return result;
    }
    
    /**
     * Display prints the toString for each book in the most current ArrayList
     * @param bookcase Bookcase object that contains the up-to-date ArrayList of Book objects
     */
    public static void display(Bookcase bookcase) {
        System.out.println("<< DISPLAY BOOKCASE >>\n" + bookcase.toString());
    }
    
    /**
     * Add a new Book object to the ArrayList as specified by user input, calls the corresponding validation methods to prevent bad data from user input
     * @param bookcase Bookcase object that contains the up-to-date ArrayList of Book objects
     */
    public static void add(Bookcase bookcase) throws FileNotFoundException {
        System.out.println("<< ADD BOOK >>\n");
        System.out.print("Enter the 13 digit ISBN of the book, digits only without dashes or spaces (ie: 9783161484100): ");
        String isbn = getValidIsbn();
        System.out.print("Enter the author of the book: ");
        String author = getValidString();
        System.out.print("Enter the title of the book: ");
        String title = getValidString();
        System.out.print("Enter the publisher of the book: ");
        String publisher = getValidString();
        System.out.print("Enter the publication year of the book, as a 4 digit integer (ie: 1970): ");
        int pubYear = getInRangeInt(1970, 2019);
        System.out.print("Enter the publication month of the book, as an integer (ie: JAN = 1, DEC = 12): ");
        int pubMonth = getInRangeInt(1, 12);
        System.out.print("Enter the publication day of the book, as an integer: ");
        int pubDay = getInRangeInt(1, 31);
        System.out.print("Enter the page count of the book as an integer: ");
        int pageCount = getInRangeInt(0, Integer.MAX_VALUE);
        bookcase.add(new Book(isbn, author, title, publisher, pubYear, pubMonth, pubDay, pageCount));
        System.out.println("\n" + title + " successfully added, thank you.\n");
    }
    
    /**
     * Delete a Book object from the ArrayList as specified by user input (book ISBN), calls validation method to prevent bad data from user input
     * @param bookcase Bookcase object that contains the up-to-date ArrayList of Book objects
     */
    public static void delete(Bookcase bookcase) throws FileNotFoundException {
        System.out.println("<< DELETE BOOK >>\n");
        System.out.print("Enter the 13 digit ISBN of the book, digits only without dashes or spaces (ie: 9783161484100): ");
        System.out.println(bookcase.delete(getValidString()));
    }
    
    /**
     * Exit program after first saving the new updated library ArrayList of Book objects to the same file
     * @param bookcase Bookcase object that contains the up-to-date ArrayList of Book objects
     */
    public static void exit(Bookcase bookcase) throws FileNotFoundException {      
        bookcase.writeFile();
        System.out.println("<< EXIT >>\n\nLibrary file successfully saved, goodbye.");
    }
}