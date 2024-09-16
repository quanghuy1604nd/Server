/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package payload;

import java.util.UUID;

/**
 *
 * @author quanghuy
 */
public class RankPayload extends Payload{
    private UUID examUserDetailId;
    private UUID examUserId;

    public RankPayload() {
    }
    public RankPayload(LogPayload payload) {
        super(payload.username, payload.clientInfo, payload.aliasName);
        this.examUserDetailId = payload.getExamUserDetailId();
        this.examUserId = payload.getExamUserId();
    }

    public RankPayload(String username, String clientInfo, String aliasName, UUID examUserDetailId, UUID examUserId) {
        super(username, clientInfo, aliasName);
        this.examUserDetailId = examUserDetailId;
        this.examUserId = examUserId;
    }

    public UUID getExamUserDetailId() {
        return examUserDetailId;
    }

    public void setExamUserDetailId(UUID examUserDetailId) {
        this.examUserDetailId = examUserDetailId;
    }

    public UUID getExamUserId() {
        return examUserId;
    }

    public void setExamUserId(UUID examUserId) {
        this.examUserId = examUserId;
    }
    
    
}
