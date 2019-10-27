import java.io.*;
import java.util.*;

/**
 * @author Nathaniel Wieck
 * @version 16 JUN 2019
 * 
 * BookCatalog ListedList maintains the catalog of Book nodes, class also contains all methods needed to manipulate
 * the data like various add, find, and delete methods. 
 * 
 */
public class BookCatalog {
	
	private BookNode front;
	private int size;
	
	public BookCatalog() {
		this.front = null;
		this.size = 0;
	}
	
	public void add(Book data) {
		if(this.front == null) {
			this.front = new BookNode(data);
		} else {
			BookNode current = this.front;
			while(current.next != null) {
				current = current.next;
			}
			current.next = new BookNode(data);
		}
		this.size++;
	}
	
	public boolean exist(String isbn) {
		BookNode current = this.front;
		while(current != null) {
			if(current.data.getIsbn().equals(isbn)) {
				
				return true;
			}
			current = current.next;
		}
		
		return false;
	}
	
	public String find(String isbn) {
		BookNode current = this.front;
		while(current != null) {
			if(current.data.getIsbn().equals(isbn)) {
				
				return current.data.toString();
			}
			current = current.next;
		}
		
		return "There are zero books with ISBN " + isbn + " in this catalog.";
	}
	
	public String findAll(String last, String first) {
		String result = "";
		int count = 0;
		BookNode current = this.front;
		while(current != null) {
			if(current.data.getLastName().equals(last) && current.data.getFirstName().equals(first)) {
				result += current.data.toString();
				count++;
			}
			current = current.next;
		}
		
		if(count == 0) {
			
			return "There are zero books by the author " + first + " " + last + " in this catalog.";
		} else {
			
			return result;
		}
	}
	
	public String findPrice(double price) {
		String result = "";
		int count = 0;
		BookNode current = this.front;
		while(current != null) {
			if(current.data.getPrice() < price) {
				result += current.data.toString();
				count++;
			}
			current = current.next;
		}
		
		if(count == 0) {			
			return "There are zero books less than $" + price + " in this catalog.";
		} else {
			
			return result;
		}
	}
	
	public String delete(String isbn) {
		BookNode current = this.front;
		for(int i = 0; i < this.size; i++) {
			if(current.data.getIsbn().equals(isbn)) {
				if (i==0) {
					if (this.size == 1) {
						this.front = null;
					} else {
						this.front = current.next;
					}	
				}
				else {
					current = this.front;
					for(int j = 0; j < i-1; j++){
						current = current.next;
					}
					current.next = current.next.next;	
				}
				
				this.size--;
				return "Book (" + isbn + ") successfully deleted.";
			}
			current = current.next;
		}
		
		return "There are zero books with ISBN " + isbn + " in this catalog, so nothing was deleted.";
	}
	
	public String deleteAll(String last, String first) {
		int count = 0;
		BookNode current = this.front;
		String result = "";
		while(current != null) {
			if(current.data.getLastName().equals(last) && current.data.getFirstName().equals(first)) {
				count++;
				String isbn = current.data.getIsbn();
				BookNode tempCurrent = this.front;
				result += current.data.toString();
				for(int i = 0; i < this.size; i++) {
					if(tempCurrent.data.getIsbn().equals(isbn)) {
						if (i==0) {
							if (this.size == 1) {
								this.front = null;
							} else {
								this.front = tempCurrent.next;
							}	
						}
						else {
							tempCurrent = this.front;
							for(int j = 0; j < i-1; j++){
								tempCurrent = tempCurrent.next;
							}
							tempCurrent.next = tempCurrent.next.next;	
						}
						
						this.size--;
					}
					tempCurrent = tempCurrent.next;
				}			
				
			}
			current = current.next;
		}
		
		if(count == 0) {
			
			return "There are zero books by the author " + first + " " + last + " in this catalog, so nothing was deleted.";
		} else {
			
			return "Successfully deleted " + count + " books by the author " + first + " " + last + " in this catalog.\n" + result;
		}
	}
	
	public String save() throws FileNotFoundException {
		File file = new File("booklist.txt");
        PrintStream output = new PrintStream(file);
        BookNode current = this.front;
        while(current.next != null) {
        	output.print(current.data.toString());
        	current = current.next;
        }
        
        return "File successfully saved.";
	}
	
	public String toString() {
		if(this.front == null) {
			
			return "There are currently 0 books in this catalog.";
		} else {
			BookNode current = this.front;
			String result = current.data.toString();
			while(current.next != null) {
				current = current.next;
				result += current.data.toString();
			}
			
			return result;
		}
	}
}