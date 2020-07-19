package com.mail.main.Controller;
/* Author: Aritra Saha */

import com.mail.main.Impl.SendMailApplicationImpl;
import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;


@RestController
public class SendMailApplicationController {
    private final static Logger log = LoggerFactory.getLogger(SendMailApplicationController.class);
    @Autowired
    SendMailApplicationImpl mailApplicationImpl;

    @RequestMapping("/mail/sendMail")
    public String sendMailService(
            @RequestParam(value="to", required = false) String toMailAddress,
            @RequestParam(value="cc", required = false) String ccMailAddress,
            @RequestParam(value="bcc", required = false) String bccMailAddress,
            @RequestParam(value="splitter", required = false) String splitter) {
        List<String> toList = new ArrayList<>();
        List<String> ccList = new ArrayList<>();
        List<String> bccList = new ArrayList<>();

        if(splitter.equalsIgnoreCase("n")){
            splitter = "\n";
        }else{
            splitter = ",";
        }
        if(null!= toMailAddress && toMailAddress.length() > 0)
            toList = Arrays.asList(toMailAddress.split(splitter));
        if(null != ccMailAddress && ccMailAddress.length() > 0)
            ccList = Arrays.asList(ccMailAddress.split(splitter));
        if(null != bccMailAddress && bccMailAddress.length() > 0)
            bccList = Arrays.asList(bccMailAddress.split(splitter));



        log.info("before sending mail");
        String messageStatus = mailApplicationImpl.sendMail(toList, ccList, bccList);
        log.info("message sent");
        return messageStatus;
    }
}
