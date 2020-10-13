package by.epam.travel_agency.model.service.impl;

import by.epam.travel_agency.exception.DaoException;
import by.epam.travel_agency.exception.ServiceException;
import by.epam.travel_agency.model.dao.impl.TourDaoImpl;
import by.epam.travel_agency.model.entity.Tour;
import by.epam.travel_agency.model.service.TourService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TourServiceImpl implements TourService {
    private static final TourServiceImpl INSTANCE = new TourServiceImpl();
    private static Logger logger = LogManager.getLogger(TourServiceImpl.class);

    private TourServiceImpl() {
    }

    public static TourServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Tour> findAll() throws ServiceException {
        List<Tour> result;
        TourDaoImpl dao = TourDaoImpl.getInstance();
        try {
            result = dao.findAll();
            logger.info("Find all tours.");
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }


}