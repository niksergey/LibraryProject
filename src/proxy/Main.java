package proxy;

import library.models.Book;

import java.lang.reflect.Proxy;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        ProxyCollection proxyCollection = new ProxyCollection();
        Set<Book> catalog = (Set<Book>) Proxy.newProxyInstance(
                MySet.class.getClassLoader(), MySet.class.getInterfaces(),
                new ProxyCollection());
        System.out.println(catalog.contains(2));
    }
}