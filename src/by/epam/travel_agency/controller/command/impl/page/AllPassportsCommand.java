package by.epam.travel_agency.controller.command.impl.page;

import by.epam.travel_agency.controller.AttributeName;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.exception.ServiceException;
import by.epam.travel_agency.model.entity.ClientPassport;
import by.epam.travel_agency.model.service.PassportService;
import by.epam.travel_agency.model.service.impl.PassportServiceImpl;
import by.epam.travel_agency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AllPassportsCommand implements Command {
    private static Logger logger = LogManager.getLogger(AllPassportsCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int idUser = (int) session.getAttribute(AttributeName.ID_USER);
        PassportService service = PassportServiceImpl.getInstance();
        String page;
        try {
            List<ClientPassport> passports = service.findPassportsByIdUser(idUser);
            session.setAttribute(AttributeName.PASSPORTS, passports);
            page = PathManager.getProperty(PathManager.PAGE_USER_PASSPORTS);
        } catch (ServiceException e) {
            logger.error(e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}