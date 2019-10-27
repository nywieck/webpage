/** test overall program functionality of book catalog and manager */

public class TestBookCatalog {

	public static void main(String[] args) {
		//create new empty BookCatalog linked list (size = 0, front = null)
		BookCatalog cat1 = new BookCatalog();
		
		//[TEST VALIDATION] print the empty BookCatalog linked list, 0 books
		System.out.println(cat1.toString());
		
		//create a new Book object
		Book book1 = new Book("1-234-56789-0", "Caca", "Beebee", "The Adventures of El Capitan", 2018, 99.86);
		
		//[TEST VALIDATION] print the Book object toString
		System.out.println(book1.toString());
		
		//add the new Book to the BookCatalog linked list
		cat1.add(book1);
		
		//the BookCatalog field front was null but now creates new BookNode with
		//the book1 data (123456789	Caca Beebee	The Adventures of El Capitan 2018 99.86) using the BookNode constructor
		//BookNode(Book data) -> BookNode(Book data, BookNode next) that thereby sets BookNode's Book data to book1's data,
		//and also creates the pointer to the next BookNode as next and is now ready to accept another Book
		//in the BookCatalog linked list

		//[TEST VALIDATION] print the new BookCatalog linked list with the newly added book
		System.out.println(cat1.toString());
		
	}
}