package by.epam.travel_agency.model.dao.impl;

import by.epam.travel_agency.exception.DaoException;
import by.epam.travel_agency.model.connection.ConnectionPool;
import by.epam.travel_agency.model.dao.ColumnName;
import by.epam.travel_agency.model.dao.OperationDao;
import by.epam.travel_agency.model.dao.StatementSql;
import by.epam.travel_agency.model.entity.SheetOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperationDaoImpl implements OperationDao {
    private static final OperationDaoImpl INSTANCE = new OperationDaoImpl();
    private static ConnectionPool pool = ConnectionPool.INSTANCE;

    private OperationDaoImpl() {
    }

    public static OperationDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<SheetOperation> findOperationsByIdSheet(int idSheet) throws DaoException {
        List<SheetOperation> result = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(StatementSql.FIND_OPERATIONS_BY_ID_SHEET)) {
            preparedStatement.setInt(1, idSheet);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SheetOperation operation = new SheetOperation();
                operation.setId(resultSet.getInt(ColumnName.ID_OPERATION));
                operation.setOperationSum(resultSet.getInt(ColumnName.OPERATION_SUM));
                operation.setOperationPurpose(resultSet.getString(ColumnName.OPERATION_PURPOSE));
                result.add(operation);
            }
        } catch (SQLException ex) {
            throw new DaoException("Exception of finding operations by id sheet.", ex);
        }
        return result;
    }

}