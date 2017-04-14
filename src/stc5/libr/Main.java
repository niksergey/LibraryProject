package stc5.libr;

import library.Library;
import library.models.*;
import library.utils.DataManager;
import library.utils.DatabaseManager;
import library.utils.SendMailTLS;
import library.utils.XMLExporter;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.HashSet;
import java.util.Set;

public class Main {
    static {
//        PropertyConfigurator.configure("log4j.properties");
    }

    private final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
//        logger.debug("Start library");

//        SendMailTLS.sendMail("me@nikser.ru", "Test message", "If you see this message program works");

        final boolean serialize = true;

        Library library = new Library();

        Book bookPHP = new Book("Ullman", "PHP for the Web", 2017, "978-0321733450");
        Book astralBody = new Book("Донцова Д.", "Астральное тело холостяка", 2016, "978-5-699-93058-6");

        DatabaseManager databaseManager = new DatabaseManager();

        databaseManager.clearTable();
        databaseManager.insert(bookPHP);
        databaseManager.select();

        bookPHP.setAuthor("Tolkien");
        databaseManager.update(bookPHP);
        databaseManager.insert(astralBody);
        databaseManager.select();

        databaseManager.delete(bookPHP);
        System.out.println("After removing book");
        databaseManager.select();

        System.out.println("");


        XMLExporter.classMetaInfoToXML("Book", "book.xml", astralBody);


        if (!serialize) {
            Set<Book> books = new HashSet<>();
            books = DataManager.deserialize(books, "books.txt");
            System.out.println("books count: " + books.size());
            for(Book book : books)
                library.buyBook(book.getTitle(), book.getAuthor(), book.getIsbn(), 1, book.getYear());
        }

//        Reader john = new Reader("John", "Connor", "Androidovich", 12345678);
//        Reader sara = new Reader("Sara", "Connor", "Human", 12345679);

        if (serialize) {
            library.buyBook("Intro to Java", "Schildt", "1241241ada", 5, 2017);
            library.buyBook("How to hack Pentagon", "Snowden", "54524dfh", 5, 2015);
        }

        BookInstance fisrtInst = library.getStore().iterator().next();

        XMLExporter.classMetaInfoToXML("BookInstance", "bookInstance.xml", fisrtInst);

        library.takeBook("John", "Connor", "Androidovich", 12345678,
                "Intro to Java");
        library.takeBook("Sara", "Connor", "Human", 12345679,
                "How to hack Pentagon");

        library.returnBook("John", "Connor", "Androidovich", 12345678,
                "Intro to Java");

        if (!serialize) {
            Set<Booking> bookings = new HashSet<>();
            bookings = DataManager.deserialize(bookings, "bookings.txt");
            for (Booking booking : bookings)
                System.out.println("Book instance: " + booking.getBookInstance() + ", Reader: " + booking.getReader());
        }

        library.showAllData();

        if (serialize) {
            Set<Booking> bookingsToWrite = library.getBookings();
            DataManager.serializeToFile(bookingsToWrite, "bookings.txt");

            Set<Book> catalog = library.getCatalog();
            DataManager.serializeToFile(catalog, "books.txt");
        }
    }
}
