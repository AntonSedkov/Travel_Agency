package by.epam.travel_agency.util.mail;

import by.epam.travel_agency.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailManager {
    private static Logger logger = LogManager.getLogger(SendMailManager.class);
    private MimeMessage message;
    private String sendToEmail;
    private String mailSubject;
    private String mailText;

    public SendMailManager(String sendToEmail, String mailSubject, String mailText) {
        this.sendToEmail = sendToEmail;
        this.mailSubject = mailSubject;
        this.mailText = mailText;
    }

    public void send() {
        try {
            initMessage();
            Transport.send(message);
            logger.info("Email has sent successfully.");
        } catch (AddressException e) {
            logger.error("Invalid address: " + sendToEmail + "  " + e);
        } catch (MessagingException e) {
            logger.error("Error generating or sending message: " + e);
        }
    }

    private void initMessage() throws MessagingException {
        Session mailSession = null;
        try {
            mailSession = MailSessionConfigurer.createSession();
        } catch (ServiceException e) {
            logger.fatal("Mail session doesn't been configured", e);
        }
        mailSession.setDebug(false);
        message = new MimeMessage(mailSession);
        message.setSubject(mailSubject);
        message.setContent(mailText, "text/html");
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
    }

}