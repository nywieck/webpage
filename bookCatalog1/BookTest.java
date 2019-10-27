import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class BookTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class BookTest
{
    /**
     * Default constructor for test class BookTest
     */
    public BookTest()
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
    public void testConstructor() {
        Book testBook = new Book("1122334455667", "Dr. Suess", "The Cat In The Hat", "Amazon Books INC", 2000, 6, 15, 29);
        assertTrue(testBook.getIsbn().equals("1122334455667"));
        assertTrue(testBook.getAuthor().equals("Dr. Suess"));
        assertTrue(testBook.getTitle().equals("The Cat In The Hat"));
        assertTrue(testBook.getPublisher().equals("Amazon Books INC"));
        assertEquals(2000, testBook.getPubYear());
        assertEquals(6, testBook.getPubMonth());
        assertEquals(15, testBook.getPubDay());
        assertEquals(29, testBook.getPageCount());
    }

    @Test
    public void testCompareTo() {
        Book testBook1 = new Book("8122334455667", "Dr. Suess", "The Cat In The Hat", "Amazon Books INC", 2000, 6, 15, 29);
        Book testBook2 = new Book("0987654321111", "Leonard Nimoy", "How To Program Like A Vulcan", "Microsoft Books Corp", 1970, 1, 1, 1);
        Book testBook3 = new Book("9000000000001", "Gandalf The Grey", "FLY YOU FOOLS!", "Google Books Company", 2019, 12, 12, 90210);
        Book testBook4 = new Book("9000000000000", "Rambo", "Survival, Evasion, Resistance, Escape For Dummies", "Facebook Books Faces", 2015, 2, 9, 174);
        Book testBook5 = new Book("9000000000000", "Rambo", "Survival, Evasion, Resistance, Escape For Dummies", "Facebook Books Faces", 2015, 2, 9, 174);
        assertTrue(testBook1.compareTo(testBook2) > 0);
        assertTrue(testBook1.compareTo(testBook3) < 0);
        assertTrue(testBook1.compareTo(testBook4) < 0);
        assertTrue(testBook2.compareTo(testBook3) < 0);
        assertTrue(testBook3.compareTo(testBook4) > 0);
        assertTrue(testBook4.compareTo(testBook5) == 0);
    }
    
}
