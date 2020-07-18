package com.mail.main.Impl;
/* Author: Aritra Saha */

import com.mail.main.Service.MailTextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;

@Component
public class SendMailApplicationImpl {
    private final static Logger log = LoggerFactory.getLogger(SendMailApplicationImpl.class);
    @Autowired
    MailTextBuilder mailTextBuilder;

    public String sendMail(List<String> toList, List<String> ccList, List<String> bccList){
        log.info("starting send mail");
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = null;
        String host="smtpout.asia.secureserver.net";
        final String user="Sigma Consultants Pvt. Ltd<info@sigmacpl.com>";//change accordingly
        final String userId = "info@sigmacpl.com";
        final String password="Sumit@2020";//change accordingly

        String to="aritras25@gmail.com";//change accordingly

        //Get the session object
        Properties props = new Properties();
        props.put("mail.smtp.host",host);
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userId,password);
                    }
                });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            toList.stream().forEach(x-> {
                try {
                    message.addRecipient(Message.RecipientType.TO,new InternetAddress(x));
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            });

            ccList.stream().forEach(x-> {
                try {
                    message.addRecipient(Message.RecipientType.CC,new InternetAddress(x));
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            });

            bccList.stream().forEach(x-> {
                try {
                    message.addRecipient(Message.RecipientType.BCC,new InternetAddress(x));
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            });
            message.setSubject(mailTextBuilder.getMailSubject());
            Multipart multipart = new MimeMultipart();

            MimeBodyPart attachmentPart = new MimeBodyPart();

            MimeBodyPart textPart = new MimeBodyPart();
            try{

                File attachmentFile = new File(getClass().getClassLoader().getResource("SigmaBrochure.pdf").getFile());
                System.out.println(attachmentFile.exists());
                attachmentPart.attachFile(attachmentFile);
                textPart.setText(mailTextBuilder.getMailText());
                multipart.addBodyPart(textPart);
                multipart.addBodyPart(attachmentPart);

            } catch (IOException e) {
                e.printStackTrace();
                log.info("Exception Happend for file attachment and file read : "+ e.getMessage());
                return "Message Couldn't send";
            }
            message.setContent(multipart);
            //send the message
            Transport.send(message);
            end = LocalDateTime.now();
            System.out.println("message sent successfully...");
        } catch (MessagingException e) {
            e.printStackTrace();
            log.info("Exception Happend: "+ e.getMessage());
            return "Message Couldn't send";
        }
        log.info("Time taken for sending mail : "+ (end.getSecond()-start.getSecond()));
        return "message sent successfully...";
    }
}
