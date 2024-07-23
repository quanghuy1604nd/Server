/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import util.AppConstants;

/**
 *
 * @author QuangHuy
 */
public class WebhookServiceImpl implements IWebhookService {

    @Override
    public void sendWebhookLogs(String ip, String username, String alias, int code, String message) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(AppConstants.WEBHOOK_LOG_ENDPOINT);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            message = ip + " | " + username + " >>> " + alias + ": Trạng thái: " + code + ", " + message;
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = message.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Webhook sent successfully.");
            } else {
                System.out.println("Failed to send webhook. Response code: " + responseCode);
            }
        } catch (IOException e) {
            System.out.println("Cannot connect to Spring Boot server");
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    @Override
    public void sendWebhookUpdateScoreBoard(Long userId) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(AppConstants.WEBHOOK_SCOREBOARD_ENDPOINT);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = (userId+"").getBytes("UTF-8");
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Webhook update scoreboard sent successfully.");
            } else {
                System.out.println("Failed to send webhook update scoreboard. Response code: " + responseCode);
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
