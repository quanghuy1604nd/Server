/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package payload;

/**
 *
 * @author QuangHuy
 */
public class ExamLogPayload extends Payload {
    private final Long examId;
    private final Long examUserId;
    private final String message;
    private final int code;

    public ExamLogPayload(Payload payload, Long examId, Long examUserId, int code, String message) {
        super(payload);
        this.examId = examId;
        this.examUserId = examUserId;
        this.code = code;
        this.message = message;
    }

    
    @Override
    public String toJson() {
        String json = String.format("%s, \"contestId\": %d, \"contestUserId\": %d, \"code\": \"%s\", \"message\": \"%s\"}",
                                        super.toJson(), examId, examUserId, code, message);
        return json;
    }
}
