package ru.sber.bankapi.service;

import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class RequestBuilder {

    public String getRequestBody(HttpExchange httpExchange) {

        StringBuilder stringBuilder = null;
        try (BufferedReader bufferedReader =
                     new BufferedReader(new InputStreamReader(httpExchange.getRequestBody(), StandardCharsets.UTF_8))) {
            int b = 0;
            stringBuilder = new StringBuilder(512);
            while (true) {
                try {
                    if ((b = bufferedReader.read()) == -1)
                        break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stringBuilder.append((char) b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

    public void setResponseBody(HttpExchange httpExchange, String responseJSON) {
        OutputStream os = httpExchange.getResponseBody();
        try {
            os.write(responseJSON.getBytes(StandardCharsets.UTF_8));
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
