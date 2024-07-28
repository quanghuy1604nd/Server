/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package payload;

/**
 *
 * @author QuangHuy
 */
public class ContestScoreBoardPayload extends Payload {

    private final Long contestId;
    private final Long contestUserId;

    public ContestScoreBoardPayload(Payload other, Long contestId, Long contestUserId) {
        super(other);
        this.contestId = contestId;
        this.contestUserId = contestUserId;
    }

    

    @Override
    public String toJson() {
        String json = String.format("%s, \"contestId\": %d, \"contestUserId\": %d}",
                                        super.toJson(), contestId, contestUserId);
        return json;
    }

}
