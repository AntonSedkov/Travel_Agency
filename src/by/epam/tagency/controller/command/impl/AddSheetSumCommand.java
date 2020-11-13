package by.epam.tagency.controller.command.impl;

import by.epam.tagency.controller.AttributeName;
import by.epam.tagency.controller.command.Command;
import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.entity.ClientSheet;
import by.epam.tagency.model.service.SheetService;
import by.epam.tagency.model.service.impl.SheetServiceImpl;
import by.epam.tagency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddSheetSumCommand implements Command {
    private static Logger logger = LogManager.getLogger(AddSheetSumCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String numberPaycard = request.getParameter(AttributeName.PAYCARD);
        HttpSession session = request.getSession();
        String page = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
        int idUser = (int) session.getAttribute(AttributeName.ID_USER);
        SheetService sheetService = SheetServiceImpl.getInstance();
        try {
            if (sheetService.addSheetSum(idUser, numberPaycard)) {
                ClientSheet sheet = sheetService.findSheetByIdUser(idUser);
                session.setAttribute(AttributeName.SHEET, sheet);
                request.setAttribute(AttributeName.ADD_SUM_RESULT, true);
                logger.info("Sum add to this sheet.");
            } else {
                request.setAttribute(AttributeName.ADD_SUM_RESULT, false);
                logger.warn("Can't add sum to this sheet.");
            }
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(AttributeName.ERROR_INFO, e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}