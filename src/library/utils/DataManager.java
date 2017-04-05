package library.utils;

import java.io.*;

/**
 * Created by sergey on 05.04.17.
 */
public class DataManager {
    public static <T> void serializeToFile(T obj, String filename) {
        try(FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(obj);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public static <T> T deserialize(T objectForType, String filename) {
        T obj = null;
        try(FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis)) {
            obj = (T) ois.readObject();
        } catch(IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            return obj;
        }
    }
}
