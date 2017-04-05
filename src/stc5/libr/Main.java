package stc5.libr;

import library.Library;
import library.models.Book;
import library.models.Booking;
import library.models.Reader;
import library.utils.DataManager;

import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Library library = new Library();

        Set<Book> books = (Set<Book>)DataManager.deserialize("books.txt");
        for(Book book : books)
            library.buyBook(book.getTitle(), book.getAuthor(), book.getIsbn(), 1, book.getYear());

        Reader john = new Reader("John", "Connor", "Androidovich", 12345678);
        Reader sara = new Reader("Sara", "Connor", "Human", 12345679);

//        library.buyBook("Intro to Java", "Schildt", "1241241ada", 5, 2017);
//        library.buyBook("How to hack Pentagon", "Snowden", "54524dfh", 5, 2015);

        library.takeBook("John", "Connor", "Androidovich", 12345678,
                "Intro to Java");
        library.takeBook("Sara", "Connor", "Human", 12345679,
                "How to hack Pentagon");

//        library.returnBook("John", "Connor", "Androidovich", 12345678,
//                "Intro to Java");

        Set<Booking> bookings = (Set<Booking>)DataManager.deserialize("bookings.txt");
        for(Booking booking: bookings)
            System.out.println("Book instance: " + booking.getBookInstance() + ", Reader: " + booking.getReader());

//        library.showAllData();

        Object bookingsToWrite = library.getBookings();
        DataManager.serializeToFile(bookingsToWrite, "bookings.txt");

//        Object obj = library.getCatalog();
//        DataManager.serializeToFile(obj, "books.txt");




    }
}
