/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package payload;

/**
 *
 * @author QuangHuy
 */
public class ExamRankPayload extends Payload {

    private final Long examId;
    private final Long examUserId;

    public ExamRankPayload(Payload other, Long examId, Long examUserId) {
        super(other);
        this.examId = examId;
        this.examUserId = examUserId;
    }

   
    @Override
    public String toJson() {
        String json = String.format("%s, \"contestId\": %d, \"contestUserId\": %d}",
                                        super.toJson(), examId, examUserId);
        return json;
    }

}
