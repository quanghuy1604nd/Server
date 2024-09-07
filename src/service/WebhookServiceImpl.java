/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import payload.ExamLogPayload;
import payload.ExamRankPayload;
import payload.Payload;
import static utils.AppConstants.WebhookConstants.*;

/**
 *
 * @author QuangHuy
 */
public class WebhookServiceImpl implements IWebhookService {
    @Override
    public void sendExamLogs(Payload payload, Long contestId, Long contestUserId, int code, String message) {
        // need send: contestId, contestUserId, message => contestId|contestUserId|message
        HttpURLConnection conn = null;
        try {
            URL url = new URL(WEBHOOK_CONTEST_LOG_ENDPOINT);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("secret-token", WEBHOOK_TOKEN);
            conn.setDoOutput(true);
            ExamLogPayload contestLogPayload = new ExamLogPayload(payload, contestId, contestUserId, code, message);
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = contestLogPayload.toJson().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Webhook sent successfully.");
            } else {
                System.out.println(contestLogPayload.toJson());
                System.out.println("CONTEST Log: Failed to send webhook. Response code: " + responseCode);
            }
        } catch (IOException e) {
            System.out.println("CONTEST Log: Cannot connect to Spring Boot server");
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    @Override
    public void sendRequestToUpdateExamRank(Payload payload, Long contestId, Long contestUserId) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(WEBHOOK_CONTEST_SCOREBOARD_ENDPOINT);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Secret-token", "graduationthesis");
            conn.setDoOutput(true);
            ExamRankPayload contestScoreBoardPayload = new ExamRankPayload(payload, contestId, contestUserId);
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = contestScoreBoardPayload.toJson().getBytes("UTF-8");
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("CONTEST SCOREBOARD: Webhook update scoreboard sent successfully.");
            } else {
                System.out.println("CONTEST SCOREBOARD:Failed to send webhook update scoreboard. Response code: " + responseCode);
            }
        } catch (IOException e) {
            // TODO: handle
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
}
