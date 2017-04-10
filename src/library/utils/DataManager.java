package library.utils;

import sun.misc.IOUtils;

import java.io.*;

/**
 * Created by sergey on 05.04.17.
 */
public class DataManager<T> {
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
        try(InputStream is = new ByteArrayInputStream(filename.getBytes());
            ObjectInputStream ois = new ObjectInputStream(is)) {
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
