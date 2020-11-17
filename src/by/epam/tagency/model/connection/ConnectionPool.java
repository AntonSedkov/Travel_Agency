package by.epam.tagency.model.connection;

import by.epam.tagency.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Timer;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public enum ConnectionPool {
    INSTANCE;

    private final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private BlockingDeque<ProxyConnection> freeConnections;
    private Queue<ProxyConnection> givenConnections;
    private Timer timer;
    private final static int DEFAULT_POOL_SIZE = 10;

    ConnectionPool() {
        freeConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        givenConnections = new ArrayDeque<>();
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnections.offer(new ProxyConnection(ConnectorDB.getConnection()));
            } catch (DaoException e) {
                logger.error(e);
            }
        }
        if (freeConnections.size() == 0) {
            throw new RuntimeException("Connection pool is not filling in");
        }
        if (freeConnections.size() < DEFAULT_POOL_SIZE) {
            while (freeConnections.size() < DEFAULT_POOL_SIZE) {
                try {
                    freeConnections.offer(new ProxyConnection(ConnectorDB.getConnection()));
                } catch (DaoException e) {
                    logger.error(e);
                }
            }
        }
        timer = new Timer();
        timer.schedule(new TimerConnectionsControl(), 60000, 120000);
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            givenConnections.offer(connection);
        } catch (InterruptedException e) {
            logger.error(e);
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection.getClass() == ProxyConnection.class
                && givenConnections.remove(connection)) {
            freeConnections.offer((ProxyConnection) connection);
        } else {
            logger.error("Attempt to insert wrong Connection to Pool");
        }
    }

    public void destroyPool() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnections.take().closeConnectionInPool();
            } catch (InterruptedException | SQLException e) {
                logger.error(e);
            }
        }
        deregisterDriver();
        timer.cancel();
        logger.info("Connection Pool was destroyed, drivers were deregistered");
    }

    int getDefaultPoolSize() {
        return DEFAULT_POOL_SIZE;
    }

    BlockingDeque<ProxyConnection> getFreeConnections() {
        return freeConnections;
    }

    int countCurrentConnectionPoolSize() {
        int currentSize = freeConnections.size() + givenConnections.size();
        return currentSize;
    }

    private void deregisterDriver() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error(e);
            }
        });
    }

}