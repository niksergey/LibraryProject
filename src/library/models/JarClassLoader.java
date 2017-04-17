package library.models;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Hashtable;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by sergey on 17.04.17.
 */
public class JarClassLoader extends ClassLoader {
    private boolean reload;
    private  String jarFile;
    private Hashtable classes = new Hashtable();

    public JarClassLoader(ClassLoader parent, String jarFile, boolean reload) {
        super(parent);
        this.reload = reload;
        this.jarFile = "notInPathClasses/" + jarFile;
    }

    public Class loadClass(String className) throws ClassNotFoundException {
        System.out.println(className);
        return findClass(className);
    }

    public  Class findClass(String className) {
        byte classByte[];
        Class result;

        result = (Class) classes.get(className);
        if (result != null) {
            return result;
        }

        if (!reload) {
            try {
                return findSystemClass(className);
            } catch (ClassNotFoundException e) {
                System.out.println("Continue our journey");
            }
        }

        try {
            JarFile jar = new JarFile(jarFile);
            System.out.println(className.replace('.', '/') + ".class");
            JarEntry entry = jar.getJarEntry(className.replace('.', '/') + ".class");
            InputStream is = jar.getInputStream(entry);
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

            int nextValue = is.read();
            while (nextValue != -1) {
                byteStream.write(nextValue);
                nextValue = is.read();
            }

            classByte = byteStream.toByteArray();
            result = defineClass(className, classByte, 0, classByte.length, null);
            classes.put(className, result);
            return result;
        } catch (IOException e) {
            return null;
        }
    }
}
