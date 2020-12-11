package ru.sber.bankapi.dao;

import ru.sber.bankapi.model.Card;

import java.sql.SQLException;
import java.util.List;

public interface ICardDao {

    void add(Card card) throws SQLException;

    List<Card> getAll() throws SQLException;

    Card getById(Long cardId) throws SQLException;

    void    update(Card card) throws SQLException;

    void    remove(Card card) throws SQLException;

}
