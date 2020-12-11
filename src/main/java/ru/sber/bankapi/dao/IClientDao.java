package ru.sber.bankapi.dao;

import ru.sber.bankapi.model.Client;

import java.sql.SQLException;
import java.util.List;

public interface IClientDao {

    void add(Client client) throws SQLException;

    List<Client> getAll() throws SQLException;

    Client getById(Long clientId) throws SQLException;

    void    update(Client client) throws SQLException;

    void    remove(Client client) throws SQLException;
}
