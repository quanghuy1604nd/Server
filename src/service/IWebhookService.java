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
    void sendExamLogs(Payload payload, Long examId, Long examUserId, int code, String message);
    void sendRequestToUpdateExamRank(Payload payload, Long examId, Long examUserId);
}
