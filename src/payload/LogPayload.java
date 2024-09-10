/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package payload;

import java.util.UUID;

/**
 *
 * @author QuangHuy
 */
public class LogPayload extends Payload {
    private int processCode;
    private String processLog;
    private UUID userId;
    private UUID examUserId;
    private UUID examUserDetailId;

    public LogPayload() {
    }

    public LogPayload(int processCode, String processLog, UUID userId, UUID examUserId, UUID examUserDetailId, Payload other) {
        super(other);
        this.processCode = processCode;
        this.processLog = processLog;
        this.userId = userId;
        this.examUserId = examUserId;
        this.examUserDetailId = examUserDetailId;
    }

    public LogPayload(String username, String clientInfo, String aliasName, int status, int processCode, String processLog, UUID userId, UUID examUserId, UUID examUserDetailId) {
        super(username, clientInfo, aliasName);
        this.processCode = processCode;
        this.processLog = processLog;
        this.userId = userId;
        this.examUserId = examUserId;
        this.examUserDetailId = examUserDetailId;
    }
    

    
    public String getProcessLog() {
        return processLog;
    }

    public void setProcessLog(String processLog) {
        this.processLog = processLog.replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t");;
    }

    public int getProcessCode() {
        return processCode;
    }

    public void setProcessCode(int processCode) {
        this.processCode = processCode;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getExamUserId() {
        return examUserId;
    }

    public void setExamUserId(UUID examUserId) {
        this.examUserId = examUserId;
    }

    public UUID getExamUserDetailId() {
        return examUserDetailId;
    }

    public void setExamUserDetailId(UUID examUserDetailId) {
        this.examUserDetailId = examUserDetailId;
    }
}
