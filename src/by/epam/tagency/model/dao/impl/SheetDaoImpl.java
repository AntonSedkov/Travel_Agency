package by.epam.tagency.model.dao.impl;

import by.epam.tagency.exception.DaoException;
import by.epam.tagency.model.connection.ConnectionPool;
import by.epam.tagency.model.dao.ColumnName;
import by.epam.tagency.model.dao.SheetDao;
import by.epam.tagency.model.dao.QuerySql;
import by.epam.tagency.model.entity.ClientSheet;
import by.epam.tagency.model.entity.DiscountType;
import by.epam.tagency.model.entity.OperationPurpose;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SheetDaoImpl implements SheetDao {
    private static Logger logger = LogManager.getLogger(SheetDaoImpl.class);
    private static final SheetDaoImpl INSTANCE = new SheetDaoImpl();
    private static ConnectionPool pool = ConnectionPool.INSTANCE;

    private SheetDaoImpl() {
    }

    public static SheetDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public ClientSheet findSheetByIdUser(int idUser) throws DaoException {
        ClientSheet result = new ClientSheet();
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySql.FIND_SHEET_BY_ID_USER)) {
            preparedStatement.setInt(1, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = createSheetFromResultSet(resultSet);
            }
        } catch (SQLException ex) {
            throw new DaoException("Exception of finding sheet by id user.", ex);
        }
        return result;
    }

    @Override
    public boolean changeSheetSum(int idUser, int newSum) throws DaoException {
        boolean result;
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySql.CHANGE_SHEET_SUM)) {
            preparedStatement.setInt(1, newSum);
            preparedStatement.setInt(2, idUser);
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new DaoException("Exception of updating sheet sum by id user.", ex);
        }
        return result;
    }

    @Override
    public boolean addSheetSum(int idUser, int numberPaycard) throws DaoException {
        boolean result = false;
        try (Connection connection = pool.getConnection()) {
            try (PreparedStatement psSheet = connection.prepareStatement(QuerySql.FIND_SHEET_BY_ID_USER, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
                 PreparedStatement psPaycard = connection.prepareStatement(QuerySql.FIND_PAYCARD_BY_NUMBER, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
                 PreparedStatement psOperation = connection.prepareStatement(QuerySql.CREATE_OPERATION)) {
                connection.setAutoCommit(false);
                psPaycard.setInt(1, numberPaycard);
                ResultSet rsPaycard = psPaycard.executeQuery();
                int sumCard = 0;
                if (rsPaycard.next()) {
                    sumCard = rsPaycard.getInt(ColumnName.CARD_SUM);
                    int quantityCard = rsPaycard.getInt(ColumnName.CARD_QUANTITY);
                    quantityCard--;
                    rsPaycard.updateInt(ColumnName.CARD_QUANTITY, quantityCard);
                    rsPaycard.updateRow();
                    logger.info("Sum take from paycard: " + numberPaycard + " left quantity: " + quantityCard);
                }
                int sheetId = 0;
                if (sumCard > 0) {
                    psSheet.setInt(1, idUser);
                    ResultSet rsSheet = psSheet.executeQuery();
                    if (rsSheet.next()) {
                        sheetId = rsSheet.getInt(ColumnName.ID_SHEET);
                        int sumSheet = rsSheet.getInt(ColumnName.SHEET_SUM);
                        sumSheet += sumCard;
                        rsSheet.updateInt(ColumnName.SHEET_SUM, sumSheet);
                        rsSheet.updateRow();
                        logger.info("Sum add to the sheet " + sheetId);
                    }
                }
                if (sheetId > 0) {
                    psOperation.setInt(1, sheetId);
                    psOperation.setInt(2, sumCard);
                    psOperation.setString(3, OperationPurpose.ADD);
                    result = psOperation.executeUpdate() > 0;
                    logger.info("Create operation" + result);
                    connection.commit();
                }
            } catch (SQLException e) {
                connection.rollback();
                throw new DaoException("Exception of adding sum to sheet from paycard in database", e);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (
                SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    private ClientSheet createSheetFromResultSet(ResultSet resultSet) throws SQLException {
        ClientSheet sheet = new ClientSheet();
        sheet.setId(resultSet.getInt(ColumnName.ID_SHEET));
        sheet.setSheetSum(resultSet.getInt(ColumnName.SHEET_SUM));
        sheet.setDiscount(DiscountType.getDiscountTypeByValue(resultSet.getInt(ColumnName.CUSTOMER_DISCOUNT)));
        return sheet;
    }

}