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

    protected String ipAddress;
    protected String alias;
    protected String username;

    public Payload() {
    }
    
   
    public Payload(Payload other) {
        this.username = other.username;
        this.ipAddress = other.ipAddress;
        this.alias = other.alias;
    }
    
    public Payload(String ipAddress, String alias, String username) {
        this.ipAddress = ipAddress;
        this.alias = alias;
        this.username = username;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String convertNotNull(String attr) {
        if (attr == null) {
            return "";
        }
        return attr;
    }
    
    public String toJson() {
        String json = String.format("{\"username\": \"%s\", \"ip\": \"%s\", \"alias\": \"%s\"",
                                        convertNotNull(username), convertNotNull(ipAddress), convertNotNull(alias));
        return json;
    };
        
}
