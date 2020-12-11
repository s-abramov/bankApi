package ru.sber.bankapi.service.handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ru.sber.bankapi.model.Account;
import ru.sber.bankapi.service.AccountService;
import ru.sber.bankapi.service.RequestBuilder;
import java.math.BigDecimal;

public class GetBalanceHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) {

        if (httpExchange.getRequestMethod().equals("GET"))
            System.out.println("This is GET");
        else if (httpExchange.getRequestMethod().equals("POST")) {
            System.out.println("This is POST");

            String requestBody = new RequestBuilder().getRequestBody(httpExchange);

            Gson gson = new Gson();
            Account account = gson.fromJson(requestBody, Account.class);
            BigDecimal accountBalance = new AccountService().getAccountBalance(account.getSelfId());
            String responseJSON = gson.toJson(accountBalance);

            new RequestBuilder().setResponseBody(httpExchange, responseJSON);
        }
    }

}
