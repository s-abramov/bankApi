package ru.sber.bankapi.dao.impl;

import ru.sber.bankapi.dao.ICardDao;
import ru.sber.bankapi.service.dss.ServiceDS;
import ru.sber.bankapi.model.Card;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardDao implements ICardDao {

    private final String SQL_ADD =
            "INSERT INTO card(self_id, account_id, card_number) VALUES (?, ?, ?)";
    private final String SQL_GET_ALL =
            "SELECT self_id, account_id, card_number FROM card";
    private final String SQL_GET_BY_ID =
            "SELECT self_id, account_id, card_number FROM card WHERE self_id = ?";
    private final String SQL_UPDATE =
            "UPDATE card SET account_id = ?, card_number = ? WHERE self_id = ?";
    private final String SQL_REMOVE = "DELETE FROM card WHERE self_id = ?";

    private final String SQL_GET_ALL_BY_ACCOUNT_ID =
            "SELECT self_id, account_id, card_number FROM card WHERE account_id=?";
    private final String SQL_NEW_CARD =
            "INSERT INTO card(account_id, card_number) VALUES (?, ?)";

    @Override
    public void add(Card card) throws SQLException {

        try (Connection connection = ServiceDS.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD)) {

            preparedStatement.setLong(1, card.getSelfId());
            preparedStatement.setLong(2, card.getAccountId());
            preparedStatement.setString(3, card.getCardNumber());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<Card> getAll() throws SQLException {
        List<Card> cardsList = new ArrayList<>();

        try (Connection connection = ServiceDS.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL);

            while (resultSet.next()) {
                Card card = new Card();
                card.setSelfId(resultSet.getLong("self_id"));
                card.setAccountId(resultSet.getLong("account_id"));
                card.setCardNumber(resultSet.getString("card_number"));
                cardsList.add(card);
            }
        }
        return cardsList;
    }

    @Override
    public Card getById(Long cardId) throws SQLException {
        Card card = new Card();

        try (Connection connection = ServiceDS.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_BY_ID)) {

            preparedStatement.setLong(1, cardId);
            ResultSet resultSet = preparedStatement.executeQuery();
            card.setSelfId(resultSet.getLong("self_id"));
            card.setAccountId(resultSet.getLong("account_id"));
            card.setCardNumber(resultSet.getString("card_number"));
            preparedStatement.executeUpdate();
        }
        return card;
    }

    @Override
    public void update(Card card) throws SQLException {
        try (Connection connection = ServiceDS.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {

            preparedStatement.setLong(1, card.getAccountId());
            preparedStatement.setString(2, card.getCardNumber());
            preparedStatement.setLong(3, card.getSelfId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void remove(Card card) throws SQLException {
        try (Connection connection = ServiceDS.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_REMOVE)) {
            preparedStatement.setLong(1, card.getSelfId());
            preparedStatement.executeUpdate();
        }
    }

    public List<Card> getAllByAccountId(Long accountId) throws SQLException {

        List<Card> cardsList = new ArrayList<>();

        try (Connection connection = ServiceDS.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ALL_BY_ACCOUNT_ID)) {

            preparedStatement.setLong(1, accountId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Card card = new Card();
                card.setSelfId(resultSet.getLong("self_id"));
                card.setAccountId(resultSet.getLong("account_id"));
                card.setCardNumber(resultSet.getString("card_number"));
                cardsList.add(card);
            }
            //preparedStatement.executeUpdate();
        }
        return cardsList;
    }

    public  void    addNewCard(Long accountId, String cardNumber) throws SQLException {
            try (Connection connection = ServiceDS.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SQL_NEW_CARD)) {

                preparedStatement.setLong(1, accountId);
                preparedStatement.setString(2, cardNumber);
                preparedStatement.executeUpdate();
            }
        }
}
