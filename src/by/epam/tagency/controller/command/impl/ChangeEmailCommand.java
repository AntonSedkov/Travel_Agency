package by.epam.tagency.controller.command.impl;

import by.epam.tagency.controller.AttributeName;
import by.epam.tagency.controller.command.Command;
import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.service.UserService;
import by.epam.tagency.model.service.impl.UserServiceImpl;
import by.epam.tagency.util.PathManager;
import by.epam.tagency.util.mail.MailTextCreator;
import by.epam.tagency.util.mail.SendMailManager;
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