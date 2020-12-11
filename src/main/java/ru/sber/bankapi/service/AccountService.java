package ru.sber.bankapi.service;

import ru.sber.bankapi.dao.impl.AccountDao;
import ru.sber.bankapi.model.Account;

import java.math.BigDecimal;
import java.sql.SQLException;

public class AccountService {

    public BigDecimal getAccountBalance(Long accountId) {

        AccountDao accountDao = new AccountDao();

        Account account = null;

        try {
            account = accountDao.getById(accountId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        BigDecimal result = account.getAccountBalance();
        if (result != null)
            return result;
        return BigDecimal.valueOf(-1.00);
    }

    public boolean     increaseBalance(Long accountId, BigDecimal amount) {
        try {
            AccountDao accountDao = new AccountDao();
            Account account = accountDao.getById(accountId);
            BigDecimal newBalance = account.getAccountBalance().add(amount);
            account.setAccountBalance(newBalance);
            accountDao.update(account);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
