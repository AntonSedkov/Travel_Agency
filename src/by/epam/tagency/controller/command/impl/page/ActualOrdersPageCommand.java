package by.epam.tagency.controller.command.impl.page;

import by.epam.tagency.controller.AttributeName;
import by.epam.tagency.controller.command.Command;
import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.entity.ClientOrder;
import by.epam.tagency.model.entity.ClientSheet;
import by.epam.tagency.model.service.OrderService;
import by.epam.tagency.model.service.TourService;
import by.epam.tagency.model.service.impl.OrderServiceImpl;
import by.epam.tagency.model.service.impl.TourServiceImpl;
import by.epam.tagency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

public class ActualOrdersPageCommand implements Command {
    private static Logger logger = LogManager.getLogger(ActualOrdersPageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int idUser = (int) session.getAttribute(AttributeName.ID_USER);
        String page;
        try {
            OrderService orderService = OrderServiceImpl.getInstance();
            List<ClientOrder> orders = orderService.findActualOrdersWithValues(idUser);
            session.setAttribute(AttributeName.ORDERS, orders);
            ClientSheet sheet = (ClientSheet) session.getAttribute(AttributeName.SHEET);
            int sheetDiscount = sheet.getDiscount().getValue();
            var ordersToPayWithSumToPay = orderService.createOrdersWithSumToPay(orders, sheetDiscount);
            session.setAttribute(AttributeName.ORDERS_WITH_SUM_TO_PAY, ordersToPayWithSumToPay);
            TourService tourService = TourServiceImpl.getInstance();
            Set<String> countries = tourService.findAvailableCountries();
            session.setAttribute(AttributeName.COUNTRIES, countries);
            page = PathManager.getProperty(PathManager.PAGE_USER_ORDERS_ACTUAL);
            logger.info("Actual order page for user " + idUser);
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(AttributeName.ERROR_INFO, e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}