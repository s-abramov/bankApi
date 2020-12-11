package ru.sber.bankapi.service.handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ru.sber.bankapi.model.Account;
import ru.sber.bankapi.model.Card;
import ru.sber.bankapi.service.CardService;
import ru.sber.bankapi.service.RequestBuilder;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class GetCardsHandler implements HttpHandler {

    /**
     *  На вход подается json методом POST, содержащий id счета
     */
    @Override
    public void handle(HttpExchange httpExchange) {

        if (httpExchange.getRequestMethod().equals("GET"))
            System.out.println("This is GET");
        else if (httpExchange.getRequestMethod().equals("POST")) {
            System.out.println("This is POST");

            String requestBody = new RequestBuilder().getRequestBody(httpExchange);

            Gson gson = new Gson();
            Account account = gson.fromJson(requestBody, Account.class);
            List<Card> cardsList = new CardService().getCardList(account.getSelfId());
            String responseJSON = gson.toJson(cardsList);

            new RequestBuilder().setResponseBody(httpExchange, responseJSON);
        }
    }

}
