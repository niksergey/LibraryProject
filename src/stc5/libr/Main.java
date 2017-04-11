package stc5.libr;

import library.Library;
import library.models.Book;
import library.models.BookInstance;
import library.models.Booking;
import library.models.Reader;
import library.utils.DataManager;
import library.utils.XMLExporter;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Book book1 = new Book("I", "the", 1988, " year born");
        XMLExporter.classMetaInfoToXML("Book", "book.xml", book1);

        Library library = new Library();

        final boolean serialize = true;

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
