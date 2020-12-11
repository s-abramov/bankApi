package ru.sber.bankapi.dao.impl;

import ru.sber.bankapi.dao.IClientDao;
import ru.sber.bankapi.service.dss.ServiceDS;
import ru.sber.bankapi.model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDao implements IClientDao {
    private final String SQL_ADD = "INSERT INTO client(self_id, client_name) VALUES (?, ?)";
    private final String SQL_GET_ALL = "SELECT self_id, client_name FROM client";
    private final String SQL_GET_BY_ID = "SELECT self_id, client_name FROM client WHERE self_id = ?";
    private final String SQL_UPDATE = "UPDATE client SET client_name = ? WHERE self_id = ?";
    private final String SQL_REMOVE = "DELETE FROM client WHERE self_id = ?";

    @Override
    public void add(Client client) throws SQLException {
        try (Connection connection = ServiceDS.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD)) {
            preparedStatement.setLong(1, client.getSelfId());
            preparedStatement.setString(2, client.getClientName());
            preparedStatement.executeUpdate();
        }
    }

    /**
     *      Лучше использовать prepared statement, а не statement.
     */
    @Override
    public List<Client> getAll() throws SQLException {
        List<Client> clientsList = new ArrayList<>();

        try (Connection connection = ServiceDS.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL);

            while (resultSet.next()) {
                Client c = new Client();
                c.setSelfId(resultSet.getLong("self_id"));
                c.setClientName(resultSet.getString("client_name"));
                clientsList.add(c);
            }
        }
        return clientsList;
    }

    @Override
    public Client getById(Long clientId) throws SQLException {
        Client client = new Client();

        try (Connection connection = ServiceDS.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_BY_ID)) {
            preparedStatement.setLong(1, clientId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                client.setSelfId(resultSet.getLong("self_id"));
                client.setClientName(resultSet.getString("client_name"));
            }
        }
        return client;
    }

    @Override
    public void update(Client client) throws SQLException {

        try (Connection connection = ServiceDS.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
            preparedStatement.setString(1, client.getClientName());
            preparedStatement.setLong(2, client.getSelfId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void remove(Client client) throws SQLException {

        try (Connection connection = ServiceDS.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_REMOVE)) {
            preparedStatement.setLong(1, client.getSelfId());
            preparedStatement.executeUpdate();
        }
    }
}
