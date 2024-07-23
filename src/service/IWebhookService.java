/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author QuangHuy
 */
public interface IWebhookService {
    void sendWebhookLogs(String ip, String username, String alias, int code, String message);

    public void sendWebhookUpdateScoreBoard(Long userId);
}
