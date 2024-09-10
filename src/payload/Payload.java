/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package payload;

/**
 *
 * @author QuangHuy
 */
public class Payload {

    protected String username;
    protected String clientInfo;
    protected String aliasName;

    public Payload() {
    }

    public Payload(Payload other) {
        this.username = other.username;
        this.clientInfo = other.clientInfo;
        this.aliasName = other.aliasName;
    }

    public Payload(String username, String clientInfo, String aliasName) {
        this.username = username;
        this.clientInfo = clientInfo;
        this.aliasName = aliasName;
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
}
