package library.models;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by sergey on 05.04.17.
 */
public class Book implements Externalizable {
    private String author;
    private String title;
    private int year;
    private String isbn;
    private static long serialVersionUID = 2L;

    public void printClassImport() {
        System.out.println(Book.class.getCanonicalName());

        for (Method method : this.getClass().getMethods()) {
            System.out.println(method.getName());
            System.out.println(method.getReturnType().getName());
            for(Parameter param: method.getParameters()) {
                System.out.println(param.getName() + " " + param.getType().getName());
            }
            System.out.println("modifier " + method.getModifiers());
        }

        try {
            for (Field field : Class.forName("library.models.Book").getDeclaredFields()) {
                field.setAccessible(true);
                System.out.println(field.getName());
                System.out.println(field.getType().getName());
                System.out.println(field.isAccessible());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void exportStructToXML() {

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element rootElement = doc.createElement("Books");
            doc.appendChild(rootElement);

            Element bookElement = doc.createElement("Book");
            rootElement.appendChild(bookElement);

            Element fieldsElement = doc.createElement("Fields");
            bookElement.appendChild(fieldsElement);

            try {
                for (Field field : Class.forName("library.models.Book").getDeclaredFields()) {
                    Element fElement = doc.createElement(field.getName());
                    fieldsElement.appendChild(fElement);
                    Attr fAttr = doc.createAttribute(field.getType().getName());
                    fAttr.setValue(field.get(this).toString());
                    fElement.setAttributeNode(fAttr);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            Element methodElement = doc.createElement("Methods");
            bookElement.appendChild(methodElement);

            for (Method method : this.getClass().getMethods()) {
                Element metElem = doc.createElement(method.getName());
                methodElement.appendChild(metElem);

                for(Parameter param: method.getParameters()) {
                    Element paramElem = doc.createElement("parameter");
                    metElem.appendChild(paramElem);
                    Attr metAttr = doc.createAttribute(param.getType().getName());
                    paramElem.setAttributeNode(metAttr);
                }

                Attr modifAttr = doc.createAttribute("isAccessible");
                modifAttr.setValue(Boolean.toString(method.isAccessible()));
                metElem.setAttributeNode(modifAttr);

                Attr returnTypeAttr = doc.createAttribute("returnType");
                returnTypeAttr.setValue(method.getReturnType().getName());
                metElem.setAttributeNode(returnTypeAttr);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("books.xml"));
            transformer.transform(source, result);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }

    public Book() {
    }

    public Book(String author, String title, int year, String isbn) {
        this.author = author;
        this.title = title;
        this.year = year;
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(author);
        out.writeObject(title);
        out.writeInt(year);
        out.writeObject(isbn);
        out.writeUTF("niksergey in " + this.getClass().getName());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.author = (String) in.readObject();
        this.title = (String) in.readObject();
        this.year = in.readInt();
        this.isbn = (String) in.readObject();
        System.out.println(in.readUTF());
    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (!(obj instanceof Book))
            return false;

        if (!this.isbn.equals(((Book) obj).isbn))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return author + "@" + title + "@" + year + "@" + isbn;
    }
}
