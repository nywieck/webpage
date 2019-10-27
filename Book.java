import java.io.*;
import java.util.*;

/**
 * @author Nathaniel Wieck
 * @version 16 JUN 2019
 * 
 * Book class for creating new book objects and retrieving book information, also for data validation (commented out)
 * 
 */
public class Book {

	private String isbn;
	private String lastName;
	private String firstName;
	private String title;
	private int year;
	private double price;
	
	public Book(String isbn, String lastName, String firstName, String title, int year, double price) {
		setIsbn(isbn);
		setLastName(lastName);
		setFirstName(firstName);
		setTitle(title);
		setYear(year);
		setPrice(price);
	}
	
//	public static String validString() {
//		String s = empty();
//		
//		return s;
//	}
	
//	public static String validName() {
//		String name = empty();
//		for(int i = 0; i < name.length(); i++) {
//			if(Character.isDigit(name.charAt(i))) {
//				System.out.println("Invalid entry - name cannot contain digits, please try again.");
//				name = empty();
//				i = 0;
//			}
//		}
//	
//		return name;
//	}
	
//	public static int validYear() {
//		Scanner in = new Scanner(System.in);
//        int year = 0;
//        boolean flag = true;
//        while(flag) {
//            if(in.hasNextInt()) {
//                year = in.nextInt();
//                if (year <= 2019) {
//                    flag = false;
//                } else {
//                    System.out.println("Invalid entry - must enter an integer equal to or less than current year (2019), try again.");
//                    //in.nextInt();
//                }
//            } else {
//                System.out.println("Invalid entry - must enter an integer, try again.");
//                in.next();
//            }
//        }
//		in.close();
//        
//		return year;
//	}
	
//	public static double validPrice() {
//		Scanner in = new Scanner(System.in);
//        double price = 0.0;
//        boolean flag = true;
//        while(flag) {
//            if(in.hasNextDouble()) {
//                price = in.nextDouble();
//                if (price >= 0.0) {
//                    flag = false;
//                } else {
//                    System.out.println("Invalid entry - price cannot be less than 0, try again.");
//                    //in.nextDouble();
//                }
//            } else {
//                System.out.println("Invalid entry - must enter a number, try again.");
//                in.next();
//            }
//        }
//        
//		return price;
//	}
	
//	public static String empty() {
//		Scanner in = new Scanner(System.in);
//		String temp = "";
//		if(in.hasNextLine()) {
//			String s = in.nextLine();
//			temp = s.replace(" ", "");
//	        while(s == null || temp.equals("")) {
//	            System.out.println("Invalid entry - must not be null or empty, try again.");
//	            s = in.nextLine();
//	            temp = s.replace(" ","");
//	        }
//		}
//	        
//	    return temp;
//		
//	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getIsbn() {
		return this.isbn;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public int getYear() {
		return this.year;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public String toString() {
		String output = "";
		output += this.isbn;
		output += "\t" + this.lastName;
		output += "\t" + this.firstName;
		output += "\t" + this.title;
		output += "\t" + this.year;
		output += "\t" + this.price + "\n";
		
		return output;
	}
}