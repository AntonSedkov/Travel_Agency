package by.epam.travel_agency.controller.command.impl;

import by.epam.travel_agency.controller.AttributeName;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.exception.ServiceException;
import by.epam.travel_agency.model.entity.UserType;
import by.epam.travel_agency.model.service.UserService;
import by.epam.travel_agency.model.service.impl.UserServiceImpl;
import by.epam.travel_agency.util.AlertManager;
import by.epam.travel_agency.util.PathManager;
import by.epam.travel_agency.util.mail.SendMailManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RegisterCommand implements Command {

    private static Logger logger = LogManager.getLogger(RegisterCommand.class);
    public static final String MAIL_SUBJECT = "Hi, you are with Mereteny team: you are registered.";
    public static final String MAIL_TEXT_GREETINGS = "<div style=\"background-color: lightblue\"><b>Hello, dear friend &#128521;. We are happy to salute you in our travel family &#128526;<br/><br/>";
    public static final String MAIL_TEXT_AUTH_DATA = "Your authenticating data:<br/><b>Login: %s<br/>Password: %s</b><br/><br/>";
    public static final String MAIL_TEXT_CONFIRMATION = "Please, confirm your email by the link below. If you didn't register, ignore this message.</b><br/>";
    public static final String MAIL_TEXT_LINK = "<a href = \"http://localhost:8089/Travel_Agency/controller?command=activate_email&user=%s\"><b>I confirm my e-mail.</b></a>";

    @Override
    public String execute(HttpServletRequest request) {
        UserService service = UserServiceImpl.getInstance();
        String page;
        String user = request.getParameter(AttributeName.USER);
        String password = request.getParameter(AttributeName.PASSWORD);
        String email = request.getParameter(AttributeName.EMAIL);
        String role = request.getParameter(AttributeName.ROLE);
        String defaultRole = UserType.USER.toString().toLowerCase();
        role = (role != null) ? role : defaultRole;
        try {
            if (service.createNewUser(user, password, email, role)) {
                if (role.equals(defaultRole)) {
                    HttpSession session = request.getSession();
                    session.setAttribute(AttributeName.USER, user);
                    session.setAttribute(AttributeName.EMAIL, email);
                    session.setAttribute(AttributeName.ROLE, role);
                    page = PathManager.getProperty(PathManager.PAGE_USER_HOME);
                } else {
                    page = (String) request.getSession().getAttribute(AttributeName.CURRENT_PAGE);
                }
                StringBuilder mailText = new StringBuilder(MAIL_TEXT_GREETINGS).append(String.format(MAIL_TEXT_AUTH_DATA, user, password))
                        .append(MAIL_TEXT_CONFIRMATION).append(String.format(MAIL_TEXT_LINK, user));
                SendMailManager sender = new SendMailManager(email, MAIL_SUBJECT, mailText.toString());
                request.setAttribute(AttributeName.REGISTER_SUCCESS, true);
                sender.send();
                logger.info(" Mail send to " + email + ".\n Log in New user + " + user);
            } else {
                request.setAttribute(AttributeName.REGISTER_ERROR, true);
                page = PathManager.getProperty(PathManager.PAGE_GUEST_REG);
            }
        } catch (ServiceException e) {
            logger.error(e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}