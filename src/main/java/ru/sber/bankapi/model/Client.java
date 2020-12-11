package ru.sber.bankapi.model;

import java.util.Objects;

public class Client {
    private Long    selfId;
    private String  clientName;

    public Client () {}

    public Long getSelfId() {
        return selfId;
    }

    public void setSelfId(Long selfId) {
        this.selfId = selfId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(selfId, client.selfId) &&
                Objects.equals(clientName, client.clientName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(selfId, clientName);
    }

    @Override
    public String toString() {
        return "Client{" +
                "selfId=" + selfId +
                ", clientName='" + clientName + '\'' +
                '}';
    }
}
