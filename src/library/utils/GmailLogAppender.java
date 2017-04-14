package library.utils;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

/**
 * Created by sergey on 14.04.17.
 */

public class GmailLogAppender extends AppenderSkeleton {

    @Override
    public void close() {

    }

    @Override
    public boolean requiresLayout() {
        return false;
    }

    @Override
    protected void append(LoggingEvent event) {

        String subj = event.getLevel().toString();
        String msgtext = layout.format(event);
        SendMailTLS.sendMail("me@nikser.ru", subj, msgtext);
    }
}