package by.epam.tagency.controller.command.impl;

import by.epam.tagency.controller.AttributeName;
import by.epam.tagency.controller.command.Command;
import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.entity.ClientOrder;
import by.epam.tagency.model.entity.ClientSheet;
import by.epam.tagency.model.entity.OrderState;
import by.epam.tagency.model.service.OrderService;
import by.epam.tagency.model.service.SheetService;
import by.epam.tagency.model.service.impl.OrderServiceImpl;
import by.epam.tagency.model.service.impl.SheetServiceImpl;
import by.epam.tagency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public class ChangeOrderStateCommand implements Command {
    private static Logger logger = LogManager.getLogger(ChangeOrderStateCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String idOrder = request.getParameter(AttributeName.ID_ORDER);
        String targetState = request.getParameter(AttributeName.TARGET_STATE);
        String page = (String) request.getSession().getAttribute(AttributeName.CURRENT_PAGE);
        try {
            switch (OrderState.valueOf(targetState.toUpperCase())) {
                case CONFIRMED -> {
                    confirmedAction(request, idOrder);
                }
                case PAID -> {
                    paidAction(request, idOrder);
                }
                case ADDED_DOCS -> {
                    addedDocsAction(request, idOrder);
                }
                case FINISHED -> {
                    finishedAction(request, idOrder);
                }
                case DECLINED -> {
                    declinedAction(request, idOrder);
                }
            }
        } catch (ServiceException | EnumConstantNotPresentException | NumberFormatException e) {
            logger.error(e);
            request.setAttribute(AttributeName.ERROR_INFO, e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

    private void confirmedAction(HttpServletRequest request, String idOrder) throws ServiceException {
        HttpSession session = request.getSession();
        OrderService service = OrderServiceImpl.getInstance();
        if (service.changeState(idOrder, OrderState.CONFIRMED)) {
            request.setAttribute(AttributeName.CONFIRM_ORDER, true);
            Map<ClientOrder, String> ordersAndUsers = service.findOrdersAndUsersToEditOrders();
            session.setAttribute(AttributeName.ORDERS_AND_USERS, ordersAndUsers);
            logger.info("Confirming order is complete for " + idOrder);
        } else {
            request.setAttribute(AttributeName.CONFIRM_ORDER, false);
            logger.warn("Confirming order is failed for " + idOrder);
        }
    }

    private void paidAction(HttpServletRequest request, String idOrder) throws ServiceException, NumberFormatException {
        HttpSession session = request.getSession();
        String sumToPay = request.getParameter(AttributeName.SUM_TO_PAY);
        int sumToPayInt = Integer.parseInt(sumToPay);
        ClientSheet sheet = (ClientSheet) session.getAttribute(AttributeName.SHEET);
        int sheetSum = sheet.getSheetSum();
        if (sheetSum >= sumToPayInt) {
            OrderService orderService = OrderServiceImpl.getInstance();
            int idUser = (int) session.getAttribute(AttributeName.ID_USER);
            if (orderService.changeState(idOrder, OrderState.PAID, String.valueOf(idUser), sumToPay)) {
                request.setAttribute(AttributeName.PAY_ORDER, true);
                List<ClientOrder> orders = orderService.findActualOrdersWithValues(idUser);
                session.setAttribute(AttributeName.ORDERS, orders);
                int sheetDiscount = sheet.getDiscount().getValue();
                var ordersToPayWithSumToPay = orderService.createOrdersWithSumToPay(idUser, orders, sheetDiscount);
                session.setAttribute(AttributeName.ORDERS_WITH_SUM_TO_PAY, ordersToPayWithSumToPay);
                SheetService sheetService = SheetServiceImpl.getInstance();
                sheet = sheetService.findSheetByIdUser(idUser);
                session.setAttribute(AttributeName.SHEET, sheet);
                logger.info("Paying order is complete for " + idOrder + " for user " + idUser);
            } else {
                request.setAttribute(AttributeName.PAY_ORDER, false);
                logger.warn("Paying order is failed for " + idOrder);
            }
        } else {
            request.setAttribute(AttributeName.SHORT_OF_MONEY, true);
            logger.warn("Not enough money for pay order " + idOrder);
        }
    }

    private void addedDocsAction(HttpServletRequest request, String idOrder) throws ServiceException {
        HttpSession session = request.getSession();
        OrderService service = OrderServiceImpl.getInstance();
        if (service.changeState(idOrder, OrderState.ADDED_DOCS)) {
            request.setAttribute(AttributeName.ADD_DOCS_ORDER, true);
            Map<ClientOrder, String> ordersAndUsers = service.findOrdersAndUsersToAddDocs();
            session.setAttribute(AttributeName.ORDERS_AND_USERS, ordersAndUsers);
            logger.info("Changing state to added docs is complete for order " + idOrder);
        } else {
            request.setAttribute(AttributeName.ADD_DOCS_ORDER, false);
            logger.warn("Changing state to added docs is failed for order " + idOrder);
        }
    }

    private void finishedAction(HttpServletRequest request, String idOrder) throws ServiceException {
        HttpSession session = request.getSession();
        OrderService service = OrderServiceImpl.getInstance();
        String comment = request.getParameter(AttributeName.COMMENT);
        int idUser = (int) session.getAttribute(AttributeName.ID_USER);
        if (service.changeState(idOrder, OrderState.FINISHED, comment)) {
            request.setAttribute(AttributeName.FINISH_ORDER, true);
            List<ClientOrder> orders = service.findAllOrdersWithValues(idUser);
            session.setAttribute(AttributeName.ORDERS, orders);
            logger.info("Finishing order is complete for " + idOrder);
        } else {
            request.setAttribute(AttributeName.FINISH_ORDER, false);
            logger.warn("Finishing order is failed for " + idOrder);
        }
    }

    private void declinedAction(HttpServletRequest request, String idOrder) throws ServiceException {
        HttpSession session = request.getSession();
        OrderService service = OrderServiceImpl.getInstance();
        String comment = request.getParameter(AttributeName.COMMENT);
        if (service.changeState(idOrder, OrderState.DECLINED, comment)) {
            request.setAttribute(AttributeName.DECLINE_ORDER, true);
            Map<ClientOrder, String> ordersAndUsers = service.findOrdersAndUsersToEditOrders();
            session.setAttribute(AttributeName.ORDERS_AND_USERS, ordersAndUsers);
            logger.info("Declining order is complete for " + idOrder);
        } else {
            request.setAttribute(AttributeName.DECLINE_ORDER, false);
            logger.warn("Declining order is failed for " + idOrder);
        }
    }

}