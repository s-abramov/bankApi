package ru.sber.bankapi.model;

import java.util.Objects;

public class Card {
    private Long selfId;
    private Long accountId;
    private String cardNumber;

    public Card() {}

    public Long getSelfId() {
        return selfId;
    }

    public void setSelfId(Long selfId) {
        this.selfId = selfId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(selfId, card.selfId) &&
                Objects.equals(accountId, card.accountId) &&
                Objects.equals(cardNumber, card.cardNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(selfId, accountId, cardNumber);
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardId=" + selfId +
                ", accountId=" + accountId +
                ", cardNumber=" + cardNumber +
                '}';
    }
}
