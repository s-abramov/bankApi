package ru.sber.bankapi.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {
    private Long        selfId;
    private Long        clientId;
    private String      accountNumber;
    private BigDecimal  accountBalance;

    public Account() {}

    public Long getSelfId() {
        return selfId;
    }

    public void setSelfId(Long selfId) {
        this.selfId = selfId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(selfId, account.selfId) &&
                Objects.equals(clientId, account.clientId) &&
                Objects.equals(accountNumber, account.accountNumber) &&
                Objects.equals(accountBalance, account.accountBalance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(selfId, clientId, accountNumber, accountBalance);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + selfId +
                ", clientId=" + clientId +
                ", account_number=" + accountNumber +
                ", balance=" + accountBalance +
                '}';
    }
}
