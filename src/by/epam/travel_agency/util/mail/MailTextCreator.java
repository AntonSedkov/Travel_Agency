package by.epam.travel_agency.util.mail;

public class MailTextCreator {
    private static final String MAIL_SUBJECT = "Hi, you are with Mereteny team: you are registered.";
    private static final String MAIL_SUBJECT_CHANGE_MAIL = "Hi, your email has been changed. Mereteny team)).";
    private static final String MAIL_TEXT_GREETINGS = "<div style=\"background-color: lightblue\"><b>Hello, dear friend &#128521;. We are happy to salute you in our travel family &#128526;<br/><br/>";
    private static final String MAIL_TEXT_AUTH_DATA = "Your authenticating data:<br/><b>Login: %s<br/>Password: %s</b><br/><br/>";
    private static final String MAIL_TEXT_CONFIRMATION = "Please, confirm your email by the link below. If you didn't register, ignore this message.</b><br/>";
    private static final String MAIL_TEXT_LINK = "<a href = \"http://localhost:8089/Travel_Agency/controller?command=activate_email&user=%s\"><b>I confirm my e-mail.</b></a>";

    private MailTextCreator() {
    }

    public static String createMailSubject() {
        return MAIL_SUBJECT;
    }

    public static String createChangeMailSubject() {
        return MAIL_SUBJECT_CHANGE_MAIL;
    }

    public static String createMailText(String user, String password) {
        StringBuilder mailText = new StringBuilder(MAIL_TEXT_GREETINGS).append(String.format(MAIL_TEXT_AUTH_DATA, user, password))
                .append(MAIL_TEXT_CONFIRMATION).append(String.format(MAIL_TEXT_LINK, user));
        return mailText.toString();
    }

    public static String createCnangeMailText(String user) {
        StringBuilder mailText = new StringBuilder(MAIL_TEXT_GREETINGS).append(MAIL_TEXT_CONFIRMATION)
                .append(String.format(MAIL_TEXT_LINK, user));
        return mailText.toString();
    }

}