package library.utils;

import library.models.Book;
import library.models.Reader;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;

import java.io.ByteArrayOutputStream;

/**
 * Created by sergey on 14.04.17.
 */
public class GmailLogLayout extends PatternLayout {

    @Override
    public String format(LoggingEvent event) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Object object = event.getMessage();
        String rootName = object.getClass().getName();

        XMLExporter.classMetaInfoToXML(rootName, baos, object);
        String res = new String(baos.toByteArray());
        return res;
    }
}
