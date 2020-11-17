package by.epam.tagency.model.connection;

import by.epam.tagency.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.TimerTask;

class TimerConnectionsControl extends TimerTask {
    private static Logger logger = LogManager.getLogger(TimerConnectionsControl.class);

    @Override
    public void run() {
        ConnectionPool pool = ConnectionPool.INSTANCE;
        int currentPoolSize = pool.countCurrentConnectionPoolSize();
        int poolSize = pool.getDefaultPoolSize();
        if (currentPoolSize > poolSize) {
            logger.error("Wrong connection in Connection Pool");
        } else if (currentPoolSize < poolSize) {
            logger.warn("Connections leak.");
            int difference = poolSize - currentPoolSize;
            for (int i = 0; i < difference; i++) {
                try {
                    pool.getFreeConnections().offer(new ProxyConnection(ConnectorDB.getConnection()));
                } catch (DaoException e) {
                    logger.error(e);
                }
            }
        } else {
            logger.info("ConnectionPool is full.");
        }
    }

}