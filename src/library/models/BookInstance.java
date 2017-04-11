package library.models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by sergey on 05.04.17.
 */
public class BookInstance implements Externalizable {
    private Book book;
    private UUID number;

    private static long serialVersionUID = 21341L;
//    private List<Booking> bookingHistory;

    public BookInstance() {}

    public BookInstance(Book book, UUID number) {
        this.book = book;
        this.number = number;
    }

    public Book getBook() {
        return book;
    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (!(obj instanceof BookInstance))
            return false;

        if (!(this.number.equals(((BookInstance) obj).number)))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return book + "@" + number;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException
    {
        out.writeObject(book);
        out.writeObject(number);
        out.writeUTF("niksergey in " + this.getClass().getName());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
    {
        this.book = (Book)in.readObject();
        this.number = (UUID) in.readObject();

        System.out.println(in.readUTF());
    }
}
