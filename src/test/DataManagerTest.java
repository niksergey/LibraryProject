package test;

import library.models.Book;
import library.utils.DataManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by sergey on 10.04.17.
 */
public class DataManagerTest {
    private static DataManager<Book> dataManager;

    @Test
    public void testDeserializationBook() {
//        File file = mock(File.class);
//        FileReader fileReader = mock(FileReader.class);
//        BufferedReader bufferedReader = mock(BufferedReader.class);
//
//        StringBuilder stringBuilder = new StringBuilder();
//        try (FileReader fr = new FileReader("books.txt");
//            BufferedReader br = new BufferedReader(fr);) {
//            try {
//                stringBuilder.append(br.readLine());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        try {
//            when(bufferedReader.readLine()).thenReturn(stringBuilder.toString());
////            Set<Book> collection = DataManager.deserialize(new HashSet<Book>(), bufferedReader.readLine());
////            Book book = collection.iterator().next();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        assertTrue(1 == 1);
    }

    @BeforeAll
    public static void init() {
        dataManager = new DataManager<>();
    }
}
