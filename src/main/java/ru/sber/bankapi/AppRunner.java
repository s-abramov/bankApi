package ru.sber.bankapi;

import ru.sber.bankapi.dao.impl.AccountDao;
import ru.sber.bankapi.dao.impl.ClientDao;
import ru.sber.bankapi.model.Account;
import ru.sber.bankapi.model.Client;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class AppRunner {
    public static void main(String[] args) {
        ClientDao clientDao = new ClientDao();
        AccountDao accountDao = new AccountDao();

        Client client = new Client();
        client.setSelfId(10L);
        client.setClientName("NEW CLIENT NAME");

        Account account = new Account();
        account.setSelfId(10L);
        account.setAccountNumber("097707070707097");
        account.setAccountBalance(BigDecimal.valueOf(1000.00));
        account.setClientId(client.getSelfId());

        try {
            List<Client> clientList = clientDao.getAll();
            for (Client c : clientList) {
                System.out.println(c);
            }
            clientDao.add(client);
            List<Client> clientListNew = clientDao.getAll();
            for (Client c : clientListNew) {
               System.out.println(c);
            }
            System.out.println("OUT!");

            List<Account> accountList = accountDao.getAll();
            for (Account a : accountList) {
                System.out.println(a);
            }
            accountDao.add(account);
            accountList = accountDao.getAll();
            for (Account a : accountList) {
                System.out.println(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //RestServer restServer = new RestServer();
        //restServer.startHttpServer();


    }
}
