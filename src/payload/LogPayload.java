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
    protected UUID userId;
    protected String username;
    protected String clientInfo;
    protected String aliasName;
    private int processCode;
    private String processLog;

    public LogPayload() {
    }
    public LogPayload(Payload payload) {
        super(payload);   
    }

    public LogPayload(UUID userId, String username, String clientInfo, String aliasName, int processCode, String processLog, Payload other) {
        super(other);
        this.userId = userId;
        this.username = username;
        this.clientInfo = clientInfo;
        this.aliasName = aliasName;
        this.processCode = processCode;
        this.processLog = processLog;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(String clientInfo) {
        this.clientInfo = clientInfo;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public int getProcessCode() {
        return processCode;
    }

    public void setProcessCode(int processCode) {
        this.processCode = processCode;
    }

    public String getProcessLog() {
        return processLog;
    }

    public void setProcessLog(String processLog) {
        this.processLog = processLog;
    }
    
}
