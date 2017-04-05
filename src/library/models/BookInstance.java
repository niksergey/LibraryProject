package library.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergey on 05.04.17.
 */
public class BookInstance {
    private Book book;
    private int number;

    private List<Booking> bookingHistory;

    public BookInstance(Book book, int number) {
        this.book = book;
        this.number = number;

        bookingHistory = new ArrayList<>(32);
    }

    @Override
    public int hashCode() {
        return number * 32;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (!(obj instanceof BookInstance))
            return false;

        if (this.number != ((BookInstance) obj).number)
            return false;

        return true;
    }

    @Override
    public String toString() {
        return book + "@" + number;
    }
}
