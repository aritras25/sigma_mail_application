
/* Author: Aritra Saha */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value= "com/mail/main")
public class SendMail {
    public static void main(String[] args) {
        SpringApplication.run(SendMail.class);
    }

}
