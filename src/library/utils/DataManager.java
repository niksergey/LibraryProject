package library.utils;

import library.models.Book;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sergey on 05.04.17.
 */
public class DataManager {
    public static void serializeToFile(Object obj, String filename) {
        try(FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(obj);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Object deserialize(String filename) {
        Object obj = null;
        try(FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis)) {
            obj =  ois.readObject();
        } catch(IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            return obj;
        }
    }


}
