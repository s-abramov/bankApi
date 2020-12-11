package ru.sber.bankapi.dao.impl;

import ru.sber.bankapi.dao.IAccountDao;
import ru.sber.bankapi.service.dss.ServiceDS;
import ru.sber.bankapi.model.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDao implements IAccountDao {
    private final String SQL_ADD =
            "INSERT INTO account(self_id, client_id, account_number, account_balance) VALUES (?, ?, ?, ?)";
    private final String SQL_GET_ALL =
            "SELECT self_id, client_id, account_number, account_balance FROM account";
    private final String SQL_GET_BY_ID =
            "SELECT self_id, client_id, account_number, account_balance FROM account WHERE self_id = ?";
    private final String SQL_UPDATE =
            "UPDATE account SET client_id = ?, account_number = ?, account_balance =? WHERE self_id = ?";
    private final String SQL_REMOVE = "DELETE FROM account WHERE self_id = ?";

    private final String SQL_GET_ALL_BY_CLIENT_ID =
            "SELECT self_id, client_id, account_number, account_balance FROM account WHERE client_id=?";
    private final String SQL_GET_BY_CLIENT_ID_ACCOUNT_ID =
            "SELECT self_id, client_id, account_number, account_balance FROM account WHERE self_id=? AND client_id=?";


    @Override
    public void add(Account account) throws SQLException {

        try (Connection connection = ServiceDS.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD)) {

            preparedStatement.setLong(1, account.getSelfId());
            preparedStatement.setLong(2, account.getClientId());
            preparedStatement.setString(3, account.getAccountNumber());
            preparedStatement.setBigDecimal(4, account.getAccountBalance());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<Account> getAll() throws SQLException {
        List<Account> accountList = new ArrayList<>();

        try (Connection connection = ServiceDS.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL);

            while (resultSet.next()) {
                Account account = new Account();
                account.setSelfId(resultSet.getLong("self_id"));
                account.setClientId(resultSet.getLong("client_id"));
                account.setAccountNumber(resultSet.getString("account_number"));
                account.setAccountBalance(resultSet.getBigDecimal("account_balance"));
                accountList.add(account);
            }
        }
        return accountList;
    }

    @Override
    public Account getById(Long accountId) throws SQLException {

        Account account = new Account();

        try (Connection connection = ServiceDS.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_BY_ID)) {

            preparedStatement.setLong(1, accountId);
            ResultSet resultSet = preparedStatement.executeQuery();
            account.setSelfId(resultSet.getLong("self_id"));
            account.setClientId(resultSet.getLong("client_id"));
            account.setAccountNumber(resultSet.getString("account_number"));
            account.setAccountBalance(resultSet.getBigDecimal("account_balance"));
            preparedStatement.executeUpdate();
        }
        return account;
    }

    @Override
    public void update(Account account) throws SQLException {

        try (Connection connection = ServiceDS.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {

            preparedStatement.setLong(1, account.getClientId());
            preparedStatement.setString(2, account.getAccountNumber());
            preparedStatement.setBigDecimal(3, account.getAccountBalance());
            preparedStatement.setLong(4, account.getSelfId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void remove(Account account) throws SQLException {

        try (Connection connection = ServiceDS.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_REMOVE)) {
            preparedStatement.setLong(1, account.getSelfId());
            preparedStatement.executeUpdate();
        }
    }

    public List<Account> getAllByClientId(Long clientId) throws SQLException {

        List<Account> accountsList = new ArrayList<>();

        try (Connection connection = ServiceDS.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ALL_BY_CLIENT_ID)) {

            preparedStatement.setLong(1, clientId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Account account = new Account();
                account.setSelfId(resultSet.getLong("self_id"));
                account.setClientId(resultSet.getLong("client_id"));
                account.setAccountNumber(resultSet.getString("account_number"));
                account.setAccountBalance(resultSet.getBigDecimal("account_balance"));
                accountsList.add(account);
            }
            //preparedStatement.executeUpdate();
        }
        return accountsList;
    }

    public Account getByClientIdAccountId(Long accountId, Long clientId) throws SQLException {

        Account account = new Account();

        try (Connection connection = ServiceDS.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_BY_CLIENT_ID_ACCOUNT_ID)){

            preparedStatement.setLong(1, accountId);
            preparedStatement.setLong(2, clientId);
            ResultSet resultSet = preparedStatement.executeQuery();
            account.setSelfId(resultSet.getLong("self_id"));
            account.setClientId(resultSet.getLong("client_id"));
            account.setAccountNumber(resultSet.getString("account_number"));
            account.setAccountBalance(resultSet.getBigDecimal("acoount_balance"));
            //preparedStatement.executeUpdate();
        }
        return account;
    }
}
