package com.mail.main.Controller;
/* Author: Aritra Saha */

import com.mail.main.Service.MailTextBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.*;


@RestController
public class SendMailApplicationController {

    @Autowired
    MailTextBuilder mailTextBuilder;

    @RequestMapping("/mail/sendMail")
    public String sendMail(
            @RequestParam(value="to", required = false) String toMailAddress,
            @RequestParam(value="cc", required = false) String ccMailAddress,
            @RequestParam(value="bcc", required = false) String bccMailAddress) {
//        String to_addr = "aritras25@gmail.com,sumitd91@gmail.com";
        List<String> toList = new ArrayList<>();
        List<String> ccList = new ArrayList<>();
        List<String> bccList = new ArrayList<>();
        if(null!= toMailAddress && toMailAddress.length() > 0)
            toList = Arrays.asList(toMailAddress.split(","));
        if(null != ccMailAddress && ccMailAddress.length() > 0)
            ccList = Arrays.asList(ccMailAddress.split(","));
        if(null != bccMailAddress && bccMailAddress.length() > 0)
            bccList = Arrays.asList(bccMailAddress.split(","));

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

                File f =new File("C:\\Users\\Aritra\\IdeaProjects\\sendMail\\src\\main\\resources\\SigmaBrochure.pdf");
                System.out.println(f.exists());
                attachmentPart.attachFile(f);
                textPart.setText(mailTextBuilder.getMailText());
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
        return "message sent successfully...";
    }
}
