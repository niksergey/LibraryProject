package stc5.libr;

import library.Library;
import library.models.*;
import library.utils.DataManager;
import library.utils.DatabaseManager;
import library.utils.XMLExporter;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.ByteArrayOutputStream;
import java.util.HashSet;
import java.util.Set;

public class Main {
    static {
        DOMConfigurator.configure("log4j_gmail.xml");
    }

    private final static Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        final boolean serialize = true;

        Library library = new Library();

        Book bookPHP = new Book("Ullman", "PHP for the Web", 2017, "978-0321733450");
        Book astralBody = new Book("Донцова Д.", "Астральное тело холостяка", 2016, "978-5-699-93058-6");

        Reader john = new Reader("John", "Cowboy", "Connor", 12345678);
        Reader sara = new Reader("Sara", "Fury", "Connor", 12345679);

        Book bk;
        Reader rd;
        for (int i = 0; i < 1000; i++) {
            if (i % 2 == 0) {
                bk = bookPHP;
                rd = john;
            }
            else {
                bk = astralBody;
                rd = sara;
            }
            LOGGER.debug(bk);
            LOGGER.debug(rd);
        }

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


//        if (!serialize) {
//            Set<Book> books = new HashSet<>();
//            books = DataManager.deserialize(books, "books.txt");
//            System.out.println("books count: " + books.size());
//            for(Book book : books)
//                library.buyBook(book.getTitle(), book.getAuthor(), book.getIsbn(), 1, book.getYear());
//        }
//

//
//        if (serialize) {
//            library.buyBook("Intro to Java", "Schildt", "1241241ada", 5, 2017);
//            library.buyBook("How to hack Pentagon", "Snowden", "54524dfh", 5, 2015);
//        }
//
//        BookInstance fisrtInst = library.getStore().iterator().next();
//
//        XMLExporter.classMetaInfoToXML("BookInstance", "bookInstance.xml", fisrtInst);
//
//        library.takeBook("John", "Connor", "Androidovich", 12345678,
//                "Intro to Java");
//        library.takeBook("Sara", "Connor", "Human", 12345679,
//                "How to hack Pentagon");
//
//        library.returnBook("John", "Connor", "Androidovich", 12345678,
//                "Intro to Java");
//
//        if (!serialize) {
//            Set<Booking> bookings = new HashSet<>();
//            bookings = DataManager.deserialize(bookings, "bookings.txt");
//            for (Booking booking : bookings)
//                System.out.println("Book instance: " + booking.getBookInstance() + ", Reader: " + booking.getReader());
//        }
//
//        library.showAllData();
//
//        if (serialize) {
//            Set<Booking> bookingsToWrite = library.getBookings();
//            DataManager.serializeToFile(bookingsToWrite, "bookings.txt");
//
//            Set<Book> catalog = library.getCatalog();
//            DataManager.serializeToFile(catalog, "books.txt");
//        }
    }
}
