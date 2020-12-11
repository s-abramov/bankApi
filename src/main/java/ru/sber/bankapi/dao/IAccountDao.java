package ru.sber.bankapi.dao;

import ru.sber.bankapi.model.Account;

import java.sql.SQLException;
import java.util.List;

public interface IAccountDao {

    void add(Account account) throws SQLException;

    List<Account> getAll() throws SQLException;

    Account getById(Long accountId) throws SQLException;

    void    update(Account account) throws SQLException;

    void    remove(Account account) throws SQLException;
}
