package ru.sber.bankapi.service;

import com.sun.net.httpserver.HttpServer;
import ru.sber.bankapi.service.handlers.GetBalanceHandler;
import ru.sber.bankapi.service.handlers.GetCardsHandler;
import ru.sber.bankapi.service.handlers.NewCardHandler;
import ru.sber.bankapi.service.handlers.SetBalanceHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class RestServer {
    private static final String HOSTNAME = "127.0.0.1";
    private static final int    HOSTPORT = 8081;
    private static final int    BACKLOG = 1;

    public void startHttpServer() {
        try {
            HttpServer httpServer = HttpServer.create(new InetSocketAddress(HOSTNAME, HOSTPORT), BACKLOG);

            httpServer.createContext("/getbalance", new GetBalanceHandler());
            httpServer.createContext("/getcards", new GetCardsHandler());
            httpServer.createContext("/setbalance", new SetBalanceHandler());
            httpServer.createContext("/newcard", new NewCardHandler());
            httpServer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
