package library.utils;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;

/**
 * Created by sergey on 14.04.17.
 */
public class GmailLogLayout extends PatternLayout {
    public String format(LoggingEvent event) {
        String msgText = (String)event.getMessage();

        return msgText;
    }
}
