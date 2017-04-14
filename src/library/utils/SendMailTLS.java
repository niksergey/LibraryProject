package library.utils;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;


public class SendMailTLS {

    public static void sendMail(String toAddress, String subj, String msgText, String fileAttactName) {
        final String username = "sergeynikolaevboox@gmail.com";
        final String password = "javaCourse5";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toAddress));
            message.setSubject(subj);

            if (fileAttactName != null) {
                MimeBodyPart messageBodyPart = new MimeBodyPart();
                Multipart multipart = new MimeMultipart();

                messageBodyPart.setText(msgText);
                DataSource source = new FileDataSource(fileAttactName);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName("attach.txt");
                multipart.addBodyPart(messageBodyPart);

                message.setContent(multipart);
            } else {
                message.setText(msgText);
            }

            Transport.send(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
