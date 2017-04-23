package main.utils;

/**
 * Created by Shoma on 14.04.2017.
 */


import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMailSSL {

public void sendEMail(String mess, String subj, String toEmail, String fileName)
{
    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.socketFactory.port", "465");
    props.put("mail.smtp.socketFactory.class",
            "javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.port", "465");

    Session session = Session.getDefaultInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("a.borokko@gmail.com","mutanabbi");
                }
            });

    try {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("a.borokko@gmail.com"));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(toEmail));
        message.setSubject(subj);
        message.setText(mess);
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText("This is message body");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
messageBodyPart = new MimeBodyPart();
String filename = fileName;
        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);
        multipart.addBodyPart(messageBodyPart);
      message.setContent(multipart);

        Transport.send(message);

        System.out.println("Done");

    } catch (MessagingException e) {
        throw new RuntimeException(e);
    }
}


}