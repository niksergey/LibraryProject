package library.models;

import com.sun.org.apache.regexp.internal.RE;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;


/**
 * Created by sergey on 05.04.17.
 */
public class Reader implements Externalizable {
    private String firstName;
    private String secondName;
    private String lastName;
    private long passportNumber;

    private String email;

    private static long serialVersionUID = 2017252L;


    public Reader() {}

    public Reader(String firstName, String secondName, String lastName, long passportNumber) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
        this.email = "mail@example.com";
    }

    @Override
    public int hashCode() {
        return (int)passportNumber * 32;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if(!(obj instanceof Reader))
            return false;

        if (passportNumber != ((Reader) obj).passportNumber)
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passportNumber=" + passportNumber +
                '}';
    }

    public long getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(long passportNumber) {
        this.passportNumber = passportNumber;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException  {
        out.writeObject(firstName);
        out.writeObject(secondName);
        out.writeObject(lastName);
        out.writeObject(passportNumber);

        out.writeUTF("niksergey in " + this.getClass().getName());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.firstName = (String) in.readObject();
        this.secondName = (String) in.readObject();
        this.lastName = (String) in.readObject();

        this.passportNumber = (long) in.readObject();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
