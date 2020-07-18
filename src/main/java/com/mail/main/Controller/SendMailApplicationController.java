package com.mail.main.Controller;
/* Author: Aritra Saha */

import com.mail.main.Impl.SendMailApplicationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;


@RestController
public class SendMailApplicationController {
    @Autowired
    SendMailApplicationImpl mailApplicationImpl;

    @RequestMapping("/mail/sendMail")
    public String sendMailService(
            @RequestParam(value="to", required = false) String toMailAddress,
            @RequestParam(value="cc", required = false) String ccMailAddress,
            @RequestParam(value="bcc", required = false) String bccMailAddress) {
        List<String> toList = new ArrayList<>();
        List<String> ccList = new ArrayList<>();
        List<String> bccList = new ArrayList<>();
        if(null!= toMailAddress && toMailAddress.length() > 0)
            toList = Arrays.asList(toMailAddress.split(","));
        if(null != ccMailAddress && ccMailAddress.length() > 0)
            ccList = Arrays.asList(ccMailAddress.split(","));
        if(null != bccMailAddress && bccMailAddress.length() > 0)
            bccList = Arrays.asList(bccMailAddress.split(","));

        String messageStatus = mailApplicationImpl.sendMail(toList, ccList, bccList);

        return messageStatus;
    }
}
