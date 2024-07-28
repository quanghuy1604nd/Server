/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package payload;

/**
 *
 * @author QuangHuy
 */
public class PracticeScoreBoardPayload extends Payload {

    private final Long userId;

    public PracticeScoreBoardPayload(Payload other, Long userId) {
        super(other);
        this.userId = userId;
    }

    

    @Override
    public String toJson() {
        String jsonPayload = String.format("%s, \"userId\": %d}",
                                        super.toJson(), userId);
        return jsonPayload;
    }
}
