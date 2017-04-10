package test;

import library.Library;
import library.models.Book;
import library.models.BookInstance;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by sergey on 10.04.17.
 */
class MainTest {

    @Test
    public void buyBookTestCatalog() {
        library.buyBook("Schildt", "Intro to Java", "1241241ada", 5, 2017);
        assertEquals(1, library.getCatalog().size());
        Book book = new Book("Intro to Java", "Schildt", 2017, "1241241ada");
        assertTrue(library.getCatalog().contains(book));
        Book bookFromCatalog = library.getCatalog().iterator().next();
        assertTrue(book.getTitle().equals(bookFromCatalog.getTitle()));
        assertTrue(book.getAuthor().equals(bookFromCatalog.getAuthor()));
        assertTrue(book.getIsbn().equals(bookFromCatalog.getIsbn()));
        assertTrue(book.getYear() == bookFromCatalog.getYear());
    }

    private static Library library;

    @BeforeAll
    public static void init() {
        library = new Library();
    }

    @AfterEach
    public void clearAll() {
        library = new Library();
    }

    @Test
    public void buyBookTestStore() {
        library.buyBook("Intro to Java", "Schildt", "1241241ada", 5, 2017);
        assertEquals(5, library.getStore().size());
        Book book = new Book("Schildt", "Intro to Java" , 2017, "1241241ada");
        for (BookInstance bookInstance : library.getStore()) {
            Book bookFromStore = bookInstance.getBook();
            assertTrue(book.getTitle().equals(bookFromStore.getTitle()));
            assertTrue(book.getAuthor().equals(bookFromStore.getAuthor()));
            assertTrue(book.getIsbn().equals(bookFromStore.getIsbn()));
            assertTrue(book.getYear() == bookFromStore.getYear());
        }
    }

    @Test
    public void takeBookTest() {
        library.buyBook("Intro to Java", "Schildt", "1241241ada", 5, 2017);
        library.buyBook("How to hack Pentagon", "Snowden", "54524dfh", 5, 2015);

        library.takeBook("John", "Connor", "Androidovich", 12345678, "Intro to Java");

        assertEquals(4, library.getStore().size());
        assertEquals(1, library.getBookings().size());

        for(int i = 0; i < 4; i++) {
            library.takeBook("John", "Connor", "Androidovich", 12345678, "Intro to Java");
        }

        assertEquals(0, library.getStore().size());
        assertTrue(library.getBookings().size() == 5);
    }

    @Test
    public void returnBookTest() {
        library.buyBook("Intro to Java", "Schildt", "1241241ada", 5, 2017);
        library.buyBook("How to hack Pentagon", "Snowden", "54524dfh", 5, 2015);

        for(int i = 0; i < 4; i++) {
            library.takeBook("John", "Connor", "Androidovich", 12345678, "Intro to Java");
        }

        library.takeBook("John", "Connor", "Androidovich", 12345678, "How to hack Pentagon");

        library.returnBook("John", "Connor", "Androidovich", 12345678, "Intro to Java");

        assertEquals(4, library.getStore().size());

        for(int i = 0; i < 3; i++) {
            library.returnBook("John", "Connor", "Androidovich", 12345678, "Intro to Java");
        }

        assertEquals(1, library.getStore().size());

        Set<BookInstance> bs = library.getStore();
        Book bookInStore = bs.iterator().next().getBook();
        Book bookExpected = new Book( "Snowden", "How to hack Pentagon", 2015, "54524dfh");
        assertTrue(bookExpected.equals(bookInStore));
    }


}