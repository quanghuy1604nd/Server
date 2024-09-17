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
    private boolean pass;

    public RankPayload() {
    }
    public RankPayload(Payload payload, boolean pass) {
        super(payload.examUserId, payload.examUserDetailId);
        this.pass = pass;
    }

    public RankPayload(UUID examUserId, UUID examUserDetailId, boolean pass) {
        super(examUserId, examUserDetailId);
        this.pass = pass;
    }
    
    public RankPayload(boolean pass) {
        this.pass = pass;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }
    
    
}
