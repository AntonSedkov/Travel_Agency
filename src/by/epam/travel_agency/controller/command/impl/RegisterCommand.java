package by.epam.travel_agency.controller.command.impl;

import by.epam.travel_agency.controller.AttributeName;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.exception.ServiceException;
import by.epam.travel_agency.model.entity.UserType;
import by.epam.travel_agency.model.service.UserService;
import by.epam.travel_agency.model.service.impl.UserServiceImpl;
import by.epam.travel_agency.util.PathManager;
import by.epam.travel_agency.util.mail.MailTextCreator;
import by.epam.travel_agency.util.mail.SendMailManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RegisterCommand implements Command {
    private static Logger logger = LogManager.getLogger(RegisterCommand.class);
    private static final String DEFAULT_ROLE = UserType.USER.toString().toLowerCase();

    @Override
    public String execute(HttpServletRequest request) {
        UserService service = UserServiceImpl.getInstance();
        String page;
        String user = request.getParameter(AttributeName.USER);
        String password = request.getParameter(AttributeName.PASSWORD);
        String email = request.getParameter(AttributeName.EMAIL);
        String role = request.getParameter(AttributeName.ROLE);
        role = (role != null) ? role : DEFAULT_ROLE;
        try {
            if (service.createNewUser(user, password, email, role)) {
                if (role.equals(DEFAULT_ROLE)) {
                    HttpSession session = request.getSession();
                    session.setAttribute(AttributeName.USER, user);
                    session.setAttribute(AttributeName.EMAIL, email);
                    session.setAttribute(AttributeName.ROLE, role);
                    page = PathManager.getProperty(PathManager.PAGE_USER_HOME);
                    logger.info("Register New user + " + user);
                } else {
                    page = (String) request.getSession().getAttribute(AttributeName.CURRENT_PAGE);
                }
                String mailSubject = MailTextCreator.createMailSubject();
                String mailText = MailTextCreator.createMailText(user, password);
                SendMailManager sender = new SendMailManager(email, mailSubject, mailText);
                sender.send();
                request.setAttribute(AttributeName.REGISTER_SUCCESS, true);
                logger.info(" Mail send to " + email);
            } else {
                request.setAttribute(AttributeName.REGISTER_ERROR, true);
                page = (role.equals(DEFAULT_ROLE))
                        ? PathManager.getProperty(PathManager.PAGE_GUEST_REG)
                        : (String) request.getSession().getAttribute(AttributeName.CURRENT_PAGE);
            }
        } catch (ServiceException e) {
            logger.error(e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}