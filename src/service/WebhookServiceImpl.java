/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import com.google.gson.Gson;
import payload.LogPayload;
import static utils.AppConstants.WebhookConstants.*;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.logging.Level;
import payload.RankPayload;
import java.util.logging.Logger;

/**
 *
 * @author QuangHuy
 */
public class WebhookServiceImpl implements IWebhookService {

    private static final Logger logger = Logger.getLogger(WebhookServiceImpl.class.getName());

    @Override
    public void sendExamLogs(LogPayload payload) {
        try {
            Gson gson = new Gson();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(WEBHOOK_EXAM_LOG_ENDPOINT)) // Webhook URL
                    .header("Content-Type", "application/json")
                    .header("Secret-Token", WEBHOOK_TOKEN)
                    .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(payload)))
                    .build();

//            HttpResponse<String> response = 
            client.send(request, HttpResponse.BodyHandlers.ofString());

//            System.out.println("Response from Spring Boot: " + response.statusCode());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "(sendExamLogs) {0}", e.getMessage());
        }
    }

    @Override
    public void sendUpdateLeaderBoard(RankPayload payload) {
        try {
            Gson gson = new Gson();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(WEBHOOK_LEADER_BOARD_ENDPOINT)) // Webhook URL
                    .header("Content-Type", "application/json")
                    .header("Secret-Token", WEBHOOK_TOKEN)
                    .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(payload)))
                    .build();

            client.send(request, HttpResponse.BodyHandlers.ofString());

//            System.out.println("Response from Spring Boot: " + response.statusCode());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "(sendUpdateLeaderBoard) {0}", e.getMessage());
        }
    }
}
