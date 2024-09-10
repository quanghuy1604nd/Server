/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.UUID;

/**
 *
 * @author quanghuy
 */
public class Alias {
    private UUID id;
    private String name;
    private UUID examUserDetailId;

    public Alias() {
    }

    public Alias(UUID id, String name, UUID examUserDetailId) {
        this.id = id;
        this.name = name;
        this.examUserDetailId = examUserDetailId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getExamUserDetailId() {
        return examUserDetailId;
    }

    public void setExamUserDetailId(UUID examUserDetailId) {
        this.examUserDetailId = examUserDetailId;
    }

    
}
