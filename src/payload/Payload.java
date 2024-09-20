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
public class Payload {

    protected UUID examUserId;
    protected UUID examUserDetailId;

    public Payload() {
    }

    public Payload(Payload other) {
        this.examUserId = other.examUserId;
        this.examUserDetailId = other.examUserDetailId;
    }
    
    public Payload(UUID examUserId, UUID examUserDetailId) {
        this.examUserId = examUserId;
        this.examUserDetailId = examUserDetailId;
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
