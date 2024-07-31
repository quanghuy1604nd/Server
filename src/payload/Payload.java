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

    protected String ip;
    protected String alias;
    protected String username;

    public Payload() {
    }
    
    public Payload(String username, String ip, String alias) {
        this.username = username;
        this.ip = ip;
        this.alias = alias;
    }
    public Payload(Payload other) {
        this.username = other.username;
        this.ip = other.ip;
        this.alias = other.alias;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
    public String convertNotNull(String attr) {
        if (attr == null) {
            return "";
        }
        return attr;
    }
    

    
    public String toJson() {
        String json = String.format("{\"username\": \"%s\", \"ip\": \"%s\", \"alias\": \"%s\"",
                                        convertNotNull(username), convertNotNull(ip), convertNotNull(alias));
        return json;
    };
        
}
