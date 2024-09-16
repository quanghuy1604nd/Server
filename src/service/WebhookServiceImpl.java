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
import java.util.UUID;
import payload.Payload;
import payload.RankPayload;

/**
 *
 * @author QuangHuy
 */
public class WebhookServiceImpl implements IWebhookService {

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

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Response from Spring Boot: " + response.body());
        } catch (Exception e) {
            e.printStackTrace();
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

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Response from Spring Boot: " + response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    @Override
//    public void sendRequestToUpdateExamRank(Payload payload, Long contestId, Long contestUserId) {
//        HttpURLConnection conn = null;
//        try {
//            URL url = new URL(WEBHOOK_CONTEST_SCOREBOARD_ENDPOINT);
//            conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("POST");
//            conn.setRequestProperty("Content-Type", "application/json");
//            conn.setRequestProperty("Secret-token", WEBHOOK_TOKEN);
//            conn.setDoOutput(true);
//            ExamRankPayload contestScoreBoardPayload = new ExamRankPayload(payload, contestId, contestUserId);
//            try (OutputStream os = conn.getOutputStream()) {
//                byte[] input = contestScoreBoardPayload.toJson().getBytes("UTF-8");
//                os.write(input, 0, input.length);
//            }
//
//            int responseCode = conn.getResponseCode();
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//                System.out.println("CONTEST SCOREBOARD: Webhook update scoreboard sent successfully.");
//            } else {
//                System.out.println("CONTEST SCOREBOARD:Failed to send webhook update scoreboard. Response code: " + responseCode);
//            }
//        } catch (IOException e) {
//            // TODO: handle
//        } finally {
//            if (conn != null) {
//                conn.disconnect();
//            }
//        }
//    }

    public void test() {
        try {
            // Sử dụng HTTP client để gửi dữ liệu tới Spring Boot
            HttpClient client = HttpClient.newHttpClient();
            LogPayload payload = new LogPayload(
                    "username",
                    "client-info",
                    "alias-name",
                    0,
                    0,
                    "processlog",
                    UUID.fromString("ed226ef1-0e45-408d-b5b0-6042fe15a3b9"),
                    UUID.fromString("ed226ef1-0e45-408d-b5b0-6042fe15a3b9"),
                    UUID.fromString("ed226ef1-0e45-408d-b5b0-6042fe15a3b9")
            );
//            System.out.println(payload.toJson());
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/v1/api/webhook/exam/logs")) // Webhook URL
                    .header("Content-Type", "application/json")
                    .header("Secret-Token", WEBHOOK_TOKEN)
                    .POST(HttpRequest.BodyPublishers.ofString(""))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Response from Spring Boot: " + response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
