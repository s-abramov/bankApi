package ru.sber.bankapi.service.dao;

import org.junit.Test;
import ru.sber.bankapi.dao.impl.AccountDao;
import ru.sber.bankapi.dao.impl.CardDao;
import ru.sber.bankapi.dao.impl.ClientDao;
import ru.sber.bankapi.model.Account;
import ru.sber.bankapi.model.Client;

import java.sql.SQLException;
import java.util.List;

public class ClientDaoTest {

    @Test
    public void test() throws SQLException{
        ClientDao clientDao = new ClientDao();

        List<Client> list = clientDao.getAll();

        for (Client client : list) {
            System.out.println(client);
        }
        Client client = new Client();
        client.setSelfId(10L);
        client.setClientName("NEW_CLIENT");
        clientDao.add(client);
        List<Client> list2 = clientDao.getAll();
        if (list.size() != list2.size())
            System.out.println("Success1");
        for (Client client2 : list2) {
            System.out.println(client2);
        }
        System.out.println(clientDao.getById(10L));
        client.setClientName("Another name");
        clientDao.update(client);
        System.out.println(clientDao.getById(10L));
        clientDao.remove(client);
        list2 = clientDao.getAll();
        if (list.size() == list2.size())
            System.out.println("Success2");
    }
}
