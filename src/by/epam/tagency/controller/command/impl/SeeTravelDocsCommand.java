package by.epam.tagency.controller.command.impl;

import by.epam.tagency.controller.AttributeName;
import by.epam.tagency.controller.command.Command;
import by.epam.tagency.exception.ServiceException;
import by.epam.tagency.model.entity.ClientOrder;
import by.epam.tagency.model.entity.TravelDocs;
import by.epam.tagency.model.service.OrderService;
import by.epam.tagency.model.service.TravelDocsService;
import by.epam.tagency.model.service.impl.OrderServiceImpl;
import by.epam.tagency.model.service.impl.TravelDocsServiceImpl;
import by.epam.tagency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SeeTravelDocsCommand implements Command {
    private static Logger logger = LogManager.getLogger(SeeTravelDocsCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String idDocs = request.getParameter(AttributeName.ID_DOCS);
        String idOrder = request.getParameter(AttributeName.ID_ORDER);
        String page;
        TravelDocsService docsService = TravelDocsServiceImpl.getInstance();
        OrderService orderService = OrderServiceImpl.getInstance();
        try {
            ClientOrder order = orderService.findConcreteOrderWithValues(idOrder);
            session.setAttribute(AttributeName.ORDER, order);
            TravelDocs docs = docsService.findTravelDocsById(idDocs);
            session.setAttribute(AttributeName.TOUR_DOCS, docs);
            page = PathManager.getProperty(PathManager.PAGE_USER_TOUR_DOCS);
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(AttributeName.ERROR_INFO, e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}