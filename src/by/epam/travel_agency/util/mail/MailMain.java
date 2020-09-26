package by.epam.travel_agency.util.mail;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class MailMain {

    public static void main(String[] args) {

        Properties properties = new Properties();
        try {
            properties.load(new FileReader("src\\configuration\\mail.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(properties);
        String mailTo = "asedkov@gmail.com";
        String subject = "Sample Mail";
        String body = "Hello java mail";
        MailSender sender = new MailSender(mailTo, subject, body, properties);
        sender.send();

    }

}