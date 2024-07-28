/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package payload;

/**
 *
 * @author QuangHuy
 */
public class ContestLogPayload extends Payload {
    private final Long contestId;
    private final Long contestUserId;
    private final String message;
    private final int code;

    public ContestLogPayload(Payload payload, Long contestId, Long contestUserId, int code, String message) {
        super(payload);
        this.contestId = contestId;
        this.contestUserId = contestUserId;
        this.code = code;
        this.message = message;
    }

    
    @Override
    public String toJson() {
        String json = String.format("%s, \"contestId\": %d, \"contestUserId\": %d, \"code\": \"%s\", \"message\": \"%s\"}",
                                        super.toJson(), contestId, contestUserId, code, message);
        return json;
    }
}
