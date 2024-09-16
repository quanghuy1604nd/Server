/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.UUID;

/**
 *
 * @author QuangHuy
 */
public class User {
    private UUID id;
    private String username;
    private String ipAddress;

    public User(UUID id, String username, String ipAddress) {
        this.id = id;
        this.username = username;
        this.ipAddress = ipAddress;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    @Override
    public String toString() {
        return "[username= " + this.username +", ipAdress= " + this.ipAddress +"]";
    }
   
}
