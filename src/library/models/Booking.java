package library.models;

import java.io.IOException;
import java.io.ObjectOutput;
import java.util.Date;
import java.io.Externalizable;
import java.io.ObjectInput;



/**
 * Created by sergey on 05.04.17.
 */
public class Booking implements Externalizable {
    private BookInstance bookInstance;
    private Reader reader;
    private Date startDate;
    private Date finishDate;
    private Date returnDate;

    private static long serialVersionUID = 2017L;

    public Booking() {

    }

    public Booking(BookInstance bookInstance, Reader reader, Date startDate, Date finishDate) {
        this.bookInstance = bookInstance;
        this.reader = reader;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(bookInstance);
        out.writeObject(reader);
        out.writeObject(startDate);
        out.writeObject(finishDate);
        out.writeObject(returnDate);
        out.writeUTF("niksergey in " + this.getClass().getName());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.bookInstance = (BookInstance) in.readObject();
        this.reader = (Reader) in.readObject();
        this.startDate = (Date) in.readObject();
        this.finishDate = (Date) in.readObject();
        this.returnDate = (Date) in.readObject();

        System.out.println(in.readUTF());
    }

    @Override
    public int hashCode() {
        return bookInstance.hashCode() + reader.hashCode() + startDate.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if(!(obj instanceof Booking))
            return false;

        if (!(bookInstance.equals(((Booking) obj).bookInstance)
                && reader.equals(((Booking) obj).reader)
                && startDate.equals(((Booking) obj).startDate)))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookInstance=" + bookInstance +
                ", reader=" + reader +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                ", returnDate=" + returnDate +
                '}';
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public BookInstance getBookInstance() {
        return bookInstance;
    }

    public Reader getReader() {
        return reader;
    }
}
