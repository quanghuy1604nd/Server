/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import payload.Payload;

/**
 *
 * @author QuangHuy
 */
public interface IWebhookService {
    void sendContestLogs(Payload payload, Long contestId, Long contestUserId, int code, String message);
    void sendRequestToUpdateContestScoreBoard(Payload payload, Long contestId, Long contestUserId);
    
    void sendPracticeLogs(Payload payload, int code, String message);
    void sendRequestToUpdatePracticeScoreBoard(Payload payload, Long userId);
}
