package ru.sber.bankapi.service;

import ru.sber.bankapi.dao.impl.CardDao;
import ru.sber.bankapi.model.Card;

import java.sql.SQLException;
import java.util.List;

public class CardService {

    public List<Card> getCardList(Long accountId) {
        CardDao cardDao = new CardDao();
        List<Card> list = null;
        try {
            list = cardDao.getAllByAccountId(accountId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean      newCard(Long accountId, String cardNumber) {
        CardDao cardDao = new CardDao();
        try {
            cardDao.addNewCard(accountId, cardNumber);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
