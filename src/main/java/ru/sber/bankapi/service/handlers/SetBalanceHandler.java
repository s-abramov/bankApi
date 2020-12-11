package ru.sber.bankapi.service.handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ru.sber.bankapi.model.Account;
import ru.sber.bankapi.service.AccountService;
import ru.sber.bankapi.service.RequestBuilder;

import java.io.IOException;

public class SetBalanceHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        if (httpExchange.getRequestMethod().equals("GET"))
            System.out.println("This is GET");
        else if (httpExchange.getRequestMethod().equals("POST")) {
            System.out.println("This is POST");
            String requestBody = new RequestBuilder().getRequestBody(httpExchange);

            Gson gson = new Gson();
            Account account = gson.fromJson(requestBody, Account.class);
            if (new AccountService().increaseBalance(account.getSelfId(), account.getAccountBalance())) {
                System.out.println("SUCCESS: balance was update");
                httpExchange.sendResponseHeaders(200, "SUCCESS".length());
            }
            else {
                System.out.println("ERROR: balance was not update");
                httpExchange.sendResponseHeaders(500, "SUCCESS".length());
            }

        }
    }
}
