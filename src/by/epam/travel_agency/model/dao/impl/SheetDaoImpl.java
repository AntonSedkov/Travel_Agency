package by.epam.travel_agency.model.dao.impl;

import by.epam.travel_agency.exception.DaoException;
import by.epam.travel_agency.model.connection.ConnectionPool;
import by.epam.travel_agency.model.dao.ColumnName;
import by.epam.travel_agency.model.dao.SheetDao;
import by.epam.travel_agency.model.dao.StatementSql;
import by.epam.travel_agency.model.entity.ClientSheet;
import by.epam.travel_agency.model.entity.DiscountType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SheetDaoImpl implements SheetDao {
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
             PreparedStatement preparedStatement = connection.prepareStatement(StatementSql.FIND_SHEET_BY_ID_USER)) {
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
             PreparedStatement preparedStatement = connection.prepareStatement(StatementSql.CHANGE_SHEET_SUM)) {
            preparedStatement.setInt(1, newSum);
            preparedStatement.setInt(2, idUser);
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new DaoException("Exception of updating sheet sum by id user.", ex);
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