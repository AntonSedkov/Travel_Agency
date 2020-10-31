package by.epam.travel_agency.controller.command.impl;

import by.epam.travel_agency.controller.AttributeName;
import by.epam.travel_agency.controller.command.Command;
import by.epam.travel_agency.exception.ServiceException;
import by.epam.travel_agency.model.entity.ClientSheet;
import by.epam.travel_agency.model.service.SheetService;
import by.epam.travel_agency.model.service.impl.SheetServiceImpl;
import by.epam.travel_agency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class PayOrderCommand implements Command {
    private static Logger logger = LogManager.getLogger(PayOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String sumOrder = request.getParameter(AttributeName.MINUSSUM);             // TODO: 31.10.2020
        HttpSession session = request.getSession();
        String page = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
        int idUser = (Integer) session.getAttribute(AttributeName.ID_USER);
        SheetService sheetService = SheetServiceImpl.getInstance();
        try {
            if (sheetService.payOrder(idUser, sumOrder)) {                  // TODO: 31.10.2020 After order
                ClientSheet sheet = sheetService.findSheetByIdUser(idUser);
                session.setAttribute(AttributeName.SHEET, sheet);
                request.setAttribute(AttributeName.PAY_ORDER_RESULT, true);
                logger.info("Order paid.");
            } else {
                request.setAttribute(AttributeName.PAY_ORDER_RESULT, false);
                logger.warn("Can't paid the order.");
            }
        } catch (ServiceException e) {
            logger.error(e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}