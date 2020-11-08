package by.epam.tagency.controller.command.impl;

import by.epam.tagency.controller.AttributeName;
import by.epam.tagency.controller.command.Command;
import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.entity.ClientOrder;
import by.epam.tagency.model.service.OrderService;
import by.epam.tagency.model.service.TravelDocsService;
import by.epam.tagency.model.service.impl.OrderServiceImpl;
import by.epam.tagency.model.service.impl.TravelDocsServiceImpl;
import by.epam.tagency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class AddOrderDocCommand implements Command {
    private static Logger logger = LogManager.getLogger(AddOrderDocCommand.class);
    private static final String VOUCHER_TYPE = "voucher";
    private static final String INSURANCE_TYPE = "insurance";
    private static final String TICKET_TYPE = "ticket";

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
        String idDocs = request.getParameter(AttributeName.ID_DOCS);
        String doctype = request.getParameter(AttributeName.DOC_TYPE);
        String imageName = (String) request.getAttribute(AttributeName.IMAGE_NAME);
        TravelDocsService docsService = TravelDocsServiceImpl.getInstance();
        try {
            boolean result = switch (doctype) {
                case VOUCHER_TYPE -> docsService.addVoucher(idDocs, imageName);
                case INSURANCE_TYPE -> docsService.addInsurance(idDocs, imageName);
                case TICKET_TYPE -> docsService.addTicket(idDocs, imageName);
                default -> false;
            };
            if (result) {
                request.setAttribute(AttributeName.ADD_DOC, true);
                Map<ClientOrder, String> ordersAndUsers;
                OrderService orderService = OrderServiceImpl.getInstance();
                ordersAndUsers = orderService.findOrdersAndUsersToAddDocs();
                session.setAttribute(AttributeName.ORDERS_AND_USERS, ordersAndUsers);
                logger.info("Add doc: " + doctype + " to docs " + idDocs);
            } else {
                request.setAttribute(AttributeName.ADD_DOC, false);
                logger.warn("Doc has not been added.");
            }
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(AttributeName.ERROR_INFO, e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}