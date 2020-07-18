
/* Author: Aritra Saha */

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class SendMailBySite {
    public static void main(String[] args) {

        String host="smtpout.asia.secureserver.net";
        final String user="info@sigmacpl.com";//change accordingly
        final String password="Sumit@2020";//change accordingly

        String to="aritras25@gmail.com";//change accordingly

        //Get the session object
        Properties props = new Properties();
        props.put("mail.smtp.host",host);
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user,password);
                    }
                });

        //Compose the message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject("javatpoint1");
            message.setText("This is simple program of sending email using JavaMail API");

            Multipart multipart = new MimeMultipart();

            MimeBodyPart attachmentPart = new MimeBodyPart();

            MimeBodyPart textPart = new MimeBodyPart();
            try {

                File f =new File("C:\\Users\\Aritra\\IdeaProjects\\sendMail\\src\\main\\resources\\SigmaBrochure.pdf");
                System.out.println(f.exists());
                attachmentPart.attachFile(f);
                textPart.setText("Test Text");
                multipart.addBodyPart(textPart);
                multipart.addBodyPart(attachmentPart);

            } catch (IOException e) {

                e.printStackTrace();

            }

            message.setContent(multipart);
            //send the message
            Transport.send(message);

            System.out.println("message sent successfully...");

        } catch (MessagingException e) {e.printStackTrace();}
    }
}
