package library;

import library.models.*;

import java.util.Set;

/**
 * Created by sergey on 05.04.17.
 */
public class Library {
    private Set<Book> catalog;
    private Set<BookInstance> store;
    private Set<Reader> readers;
    private Set<Booking> bookings;

    public Library() {
    }
}
