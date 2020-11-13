package by.epam.tagency.controller.command.impl.page;

import by.epam.tagency.controller.AttributeName;
import by.epam.tagency.controller.command.Command;
import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.entity.ClientSheet;
import by.epam.tagency.model.entity.SheetOperation;
import by.epam.tagency.model.service.OperationService;
import by.epam.tagency.model.service.impl.OperationServiceImpl;
import by.epam.tagency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SeeOperationsCommand implements Command {
    private static Logger logger = LogManager.getLogger(SeeOperationsCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ClientSheet sheet = (ClientSheet) session.getAttribute(AttributeName.SHEET);
        int idSheet = sheet.getId();
        OperationService service = OperationServiceImpl.getInstance();
        String page;
        try {
            List<SheetOperation> operations = service.findOperationsByIdSheet(idSheet);
            session.setAttribute(AttributeName.OPERATIONS, operations);
            page = PathManager.getProperty(PathManager.PAGE_USER_SEE_OPERATIONS);
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(AttributeName.ERROR_INFO, e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}