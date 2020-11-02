package by.epam.travel_agency.controller.command.impl;

import by.epam.travel_agency.controller.AttributeName;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.exception.ServiceException;
import by.epam.travel_agency.model.service.UserService;
import by.epam.travel_agency.model.service.impl.UserServiceImpl;
import by.epam.travel_agency.util.PathManager;
import by.epam.travel_agency.util.mail.MailTextCreator;
import by.epam.travel_agency.util.mail.SendMailManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeEmailCommand implements Command {
    private static Logger logger = LogManager.getLogger(ChangeEmailCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        UserService service = UserServiceImpl.getInstance();
        HttpSession session = request.getSession();
        String page = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
        String login = (String) session.getAttribute(AttributeName.USER);
        String newEmail = request.getParameter(AttributeName.NEW_EMAIL);
        try {
            if (service.changeEmail(login, newEmail)) {
                request.setAttribute(AttributeName.CHANGE_EMAIL, true);
                String mailSubject = MailTextCreator.createChangeMailSubject();
                String mailText = MailTextCreator.createChangeMailText(login);
                SendMailManager sender = new SendMailManager(newEmail, mailSubject, mailText);
                sender.send();
                logger.info("Changing email is complete for " + login);
            } else {
                request.setAttribute(AttributeName.CHANGE_EMAIL, false);
                logger.warn("Changing email is failed for " + login);
            }
        } catch (ServiceException e) {
            logger.error(e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}