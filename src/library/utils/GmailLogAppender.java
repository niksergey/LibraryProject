package library.utils;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.RollingFileAppender;
import org.apache.log4j.spi.LoggingEvent;

import java.io.*;

/**
 * Created by sergey on 14.04.17.
 */

public class GmailLogAppender extends AppenderSkeleton{

    @Override
    public void close() {

    }

    @Override
    public boolean requiresLayout() {
        return false;
    }

    @Override
    protected void append(LoggingEvent event) {

        String fileName = "logs/temp_gmail.txt";
        File attachFile = new File("logs/temp_gmail.txt");

        String subj = event.getLevel().toString();
        String msgtext = layout.format(event);

        if (attachFile.length() > 1024) {
            SendMailTLS.sendMail("me@nikser.ru", "log from Library",
                    "Please see attach", fileName);
            boolean isDeleted = attachFile.delete();
        }

        try(FileWriter fr = new FileWriter(fileName, true);
            BufferedWriter br = new BufferedWriter(fr)) {
            br.write(subj + " " + msgtext);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}