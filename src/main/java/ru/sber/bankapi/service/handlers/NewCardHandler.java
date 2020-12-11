package ru.sber.bankapi.service.handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ru.sber.bankapi.model.Card;
import ru.sber.bankapi.service.CardService;
import ru.sber.bankapi.service.RequestBuilder;

import java.io.IOException;

public class NewCardHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        if (httpExchange.getRequestMethod().equals("GET"))
            System.out.println("This is GET");
        else if (httpExchange.getRequestMethod().equals("POST")) {
            System.out.println("This is POST");

            String requestBody = new RequestBuilder().getRequestBody(httpExchange);

            Gson gson = new Gson();
            Card card = gson.fromJson(requestBody, Card.class);
            if (new CardService().newCard(card.getAccountId(), card.getCardNumber())) {
                System.out.println("SUCCESS: new card was added");
                httpExchange.sendResponseHeaders(200, "SUCCESS".length());
            }
            else {
                System.out.println("ERROR: new card was added");
                httpExchange.sendResponseHeaders(500, "SUCCESS".length());
            }

        }
    }
}
