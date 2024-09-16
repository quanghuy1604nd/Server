/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import payload.LogPayload;
import payload.Payload;
import payload.RankPayload;

/**
 *
 * @author QuangHuy
 */
public interface IWebhookService {
    void sendExamLogs(LogPayload payload);
    void sendUpdateLeaderBoard(RankPayload payload);
}
