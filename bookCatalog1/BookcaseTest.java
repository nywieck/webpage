import java.io.File;
import java.io.FileNotFoundException;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class BookcaseTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class BookcaseTest
{
    /**
     * Default constructor for test class BookcaseTest
     */
    public BookcaseTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void testBookcaseConstructor() throws FileNotFoundException {
        Bookcase testBookcase = new Bookcase("LibraryTest1.txt");
        assertTrue(testBookcase.getFileName().equals("LibraryTest1.txt"));
        assertEquals(4, testBookcase.bookCount());
        assertEquals(0, testBookcase.search("0987654321111"));
        assertEquals(1, testBookcase.search("8122334455667"));
        assertEquals(2, testBookcase.search("9000000000000"));
        assertEquals(3, testBookcase.search("9000000000001"));
        assertTrue(testBookcase.bookAt(0).getIsbn().equals("0987654321111"));
        assertTrue(testBookcase.bookAt(0).getAuthor().equals("Leonard Nimoy"));
        assertTrue(testBookcase.bookAt(0).getTitle().equals("How To Program Like A Vulcan"));
        assertTrue(testBookcase.bookAt(0).getPublisher().equals("Microsoft Books Corp"));
        assertEquals(1970, testBookcase.bookAt(0).getPubYear());
        assertEquals(1, testBookcase.bookAt(0).getPubMonth());
        assertEquals(1, testBookcase.bookAt(0).getPubDay());
        assertEquals(1, testBookcase.bookAt(0).getPageCount());
        assertEquals(2, testBookcase.search("9000000000000"));
        assertTrue(testBookcase.bookAt(2).getIsbn().equals("9000000000000"));
        assertTrue(testBookcase.bookAt(2).getAuthor().equals("Rambo"));
        assertTrue(testBookcase.bookAt(2).getTitle().equals("Survival, Evasion, Resistance, Escape For Dummies"));
        assertTrue(testBookcase.bookAt(2).getPublisher().equals("Facebook Books Faces"));
        assertEquals(2015, testBookcase.bookAt(2).getPubYear());
        assertEquals(2, testBookcase.bookAt(2).getPubMonth());
        assertEquals(9, testBookcase.bookAt(2).getPubDay());
        assertEquals(174, testBookcase.bookAt(2).getPageCount());
    }
    
    @Test
    public void testSearchNotFound() throws FileNotFoundException {
        Bookcase testBookcase = new Bookcase("LibraryTest1.txt");
        assertEquals(-1, testBookcase.search("0192837465748"));
    }
    
    @Test
    public void testDeleteBookDNE() throws FileNotFoundException {
        Bookcase testBookcase = new Bookcase("LibraryTest1.txt");
        assertTrue(testBookcase.delete("0192837465748").equals("Book is not on the bookshelf."));
    }
    
    @Test
    public void testAddBook() throws FileNotFoundException {
        Bookcase testBookcase = new Bookcase("LibraryTest1.txt");
        testBookcase.add(new Book("0000000000001", "President Donny Trump", "Everybody Poops... Except Kim Jeong Eun And Myself", "PyeongYang Printing Colony", 2018, 3, 13, 349));
        assertEquals(5, testBookcase.bookCount());
        assertTrue(testBookcase.bookAt(0).getIsbn().equals("0000000000001"));
        assertTrue(testBookcase.bookAt(0).getAuthor().equals("President Donny Trump"));
        assertTrue(testBookcase.bookAt(0).getTitle().equals("Everybody Poops... Except Kim Jeong Eun And Myself"));
        assertTrue(testBookcase.bookAt(0).getPublisher().equals("PyeongYang Printing Colony"));
        assertEquals(2018, testBookcase.bookAt(0).getPubYear());
        assertEquals(3, testBookcase.bookAt(0).getPubMonth());
        assertEquals(13, testBookcase.bookAt(0).getPubDay());
        assertEquals(349, testBookcase.bookAt(0).getPageCount());
    }
    
    @Test
    public void testDeleteBook() throws FileNotFoundException {
        Bookcase testBookcase = new Bookcase("LibraryTest1.txt");
        assertEquals(4, testBookcase.bookCount());
        testBookcase.delete("0987654321111");
        assertEquals(3, testBookcase.bookCount());
        assertEquals(-1, testBookcase.search("0987654321111"));
        assertTrue(testBookcase.delete("0987654321111").equals("Book is not on the bookshelf."));
    }
    
    @Test
    public void testClearBookshelf() throws FileNotFoundException {
        Bookcase testBookcase = new Bookcase("LibraryTest1.txt");
        assertEquals(4, testBookcase.bookCount());
        testBookcase.clearBooks();
        assertEquals(0, testBookcase.bookCount());
    }
    
    @Test (expected = FileNotFoundException.class)
    public void testPreconFileNotFoundException() throws FileNotFoundException {
        Bookcase testBookcase = new Bookcase("netherWorld9.txt");
    }
}
