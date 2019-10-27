import java.util.*;
import java.io.*;

/**
 * @author Nathaniel Wieck
 * @version 16 JUN 2019
 * 
 * BookCatalogManager is the User Interface client which consists of a series of looping menus and submenus
 * allowing the user to input their choices. The class first creates a BookCatalog LinkedList of Book nodes
 * by reading in the files "booklist.txt".
 * 
 */
public class BookCatalogManager {
	
	public static void main(String[] args) throws FileNotFoundException {
		BookCatalog catalog = createCatalog();
		Scanner input = new Scanner(System.in);
        int choice = 0;
        while(choice != 4) {
            System.out.println("[ MAIN MENU ]\nPlease enter a number corresponding to one of the following options:\n");
            System.out.println("1. Add book");
            System.out.println("2. Find book(s)");
            System.out.println("3. Delete book(s)");
            System.out.println("4. Quit program");
            choice = getInRangeInt(1, 4);
            switch (choice) {
                case 1:
                add(catalog);
                break;
                
                case 2:
                find(catalog);
                break;
                
                case 3:
                delete(catalog);
                break;
                
                case 4:
                boolean doubleCheck = quit(catalog);
                if(!doubleCheck) {
                	choice = 0;
                }
                break;
            }
        }
	}
	
	public static BookCatalog createCatalog() throws FileNotFoundException {
		BookCatalog catalog = new BookCatalog();
		Scanner input = new Scanner(new File("booklist.txt"));
		String isbn = "";
		String last = "";
		String first = "";
		String title = "";
		int year = 0;
		double price = 0.0;		
		while(input.hasNext()) {
            isbn = input.next();
            last = input.next();
            first = input.next();
            title = "";
            while(!input.hasNextInt()) {
            	title += input.next() + " ";
            }
            year = input.nextInt();
            price = input.nextDouble();
            catalog.add(new Book(isbn, last, first, title, year, price));
        }

		return catalog;
	}
	
	public static void add(BookCatalog catalog) {
		Scanner in = new Scanner(System.in);
		System.out.println("\n[ ADD BOOK ]\nPlease enter the book's 10 digit ISBN: ");
		String isbn = in.nextLine();
		if(catalog.exist(isbn)) {
			System.out.println("This book (" + isbn + ") already exists in the catalog and cannot be added again.");
		} else {
			System.out.println("Please enter the book author's last name: ");
			String last = in.nextLine();
			System.out.println("Please enter the book author's first name: ");
			String first = in.nextLine();
			System.out.println("Please enter the book's title: ");
			String title = in.nextLine();
			System.out.println("Please enter the year the book was published: ");
			int year = in.nextInt();
			System.out.println("Please enter the book's price: ");
			double price = in.nextDouble();
			catalog.add(new Book(isbn, last, first, title, year, price));
		}
	}
	
	public static void find(BookCatalog catalog) {
		Scanner in = new Scanner(System.in);
        int choice = 0;
        while(choice != 4) {
            System.out.println("\n[ FIND BOOK SUBMENU ]\nPlease enter a number corresponding to one of the following options:\n");
            System.out.println("1. Find a single book");
            System.out.println("2. Find all books by a particular author");
            System.out.println("3. Find all books whose price is less than a given value");
            System.out.println("4. Return to Main Menu");
            choice = getInRangeInt(1, 4);
            switch (choice) {
            	case 1:
        		System.out.println("\n[ FIND SINGLE BOOK ]\nPlease enter the book's 10 digit ISBN: ");
        		String isbn = in.nextLine();
        		System.out.println(catalog.find(isbn));
                break;
                
                case 2:
            	System.out.println("\n[ FIND ALL BOOKS BY SPECIFIC AUTHOR ]\nPlease enter the book author's first name: ");
            	String first = in.nextLine();
            	System.out.println("Please enter the book author's last name: ");
            	String last = in.nextLine();
            	System.out.println(catalog.findAll(last, first));
                break;
                
                case 3:
            	System.out.println("\n[ FIND ALL BOOKS LESS THAN SPECIFIC PRICE ]\nPlease enter a price: ");
            	double price = in.nextDouble();
            	System.out.println(catalog.findPrice(price));
                break;
                
                case 4:
            	System.out.println("\nReturning to Main Menu...\n");
                break;
            }
        }
	}
	
	public static void delete(BookCatalog catalog) {
		Scanner input = new Scanner(System.in);
        int choice = 0;
        while(choice != 3) {
            System.out.println("\n[ DELETE BOOK SUBMENU ]\nPlease enter a number corresponding to one of the following options:\n");
            System.out.println("1. Delete a single book");
            System.out.println("2. Delete all books by a particular author");
            System.out.println("3. Return to Main Menu");
            choice = getInRangeInt(1, 3);
            switch (choice) {
            	case 1:
        		System.out.println("\n[ DELETE SINGLE BOOK ]\nPlease enter the book's 10 digit ISBN: ");
        		String isbn = input.nextLine();
        		System.out.println(catalog.delete(isbn));
                break;
                
                case 2:
            	System.out.println("\n[ DELETE ALL BOOKS BY SPECIFIC AUTHOR ]\nPlease enter the book author's first name: ");
            	String first = input.nextLine();
            	System.out.println("Please enter the book author's last name: ");
            	String last = input.nextLine();
            	System.out.println(catalog.deleteAll(last, first));
                break;
                
                case 3:
            	System.out.println("\nReturning to Main Menu...\n");
                break;
            }
        }
	}
	
	public static boolean quit(BookCatalog catalog) throws FileNotFoundException {
		Scanner input = new Scanner(System.in);
		String choice = "";
		System.out.println("\nAre you sure you want to quit program (y/n)?");
        choice = input.next().toLowerCase();
        while(!choice.equals("y") && !choice.equals("n")) {
        	System.out.println("Invalid entry - must enter y or n, try again.");
        	choice = input.next();
        }
        if(choice.equals("y")) {
        	System.out.println("\nDo you want to save changes (y/n)?");
        	String save = "";
        	save = input.next().toLowerCase();
        	while(!choice.equals("y") && !choice.equals("n")) {
            	System.out.println("Invalid entry - must enter y or n, try again.");
            	choice = input.next();
            }
        	if(save.equals("y")) {
        		System.out.println(catalog.save());
        	} else {
        		System.out.println("Thank you, goodbye.");
        	}
        	
        	return true;
        } else {
        	System.out.println("Returning to Main Menu...");
        	
        	return false;
        }
        
	}
	
    public static int getInRangeInt(int min, int max) {
        Scanner input = new Scanner(System.in);
        int result = 0;
        boolean flag = true;
        while(flag) {
            if (input.hasNextInt()) {
                result = input.nextInt();
                if (result >= min && result <= max) {
                    flag = false;
                } else {
                    System.out.println("Invalid entry - must enter an integer between " + min + " and " + max + ", try again.");
                    input.nextInt();
                }
            } else {
                System.out.println("Invalid entry - must enter an integer between " + min + " and " + max + ", try again.");
                input.next();
            }
        }
        
        return result;
    }
}