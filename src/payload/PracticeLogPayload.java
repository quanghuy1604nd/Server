/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package payload;

/**
 *
 * @author QuangHuy
 */
public class PracticeLogPayload extends Payload{
    private final int code;
    private final String message;
    public PracticeLogPayload(Payload other, int code, String message) {
        super(other);
        this.code = code;
        this.message = message == null ? "" : message.replace("\"", "'");
    }

    @Override
    public String toJson() {
        String json = String.format("%s, \"code\": \"%s\", \"message\": \"%s\"}",
                                        super.toJson(), code, convertNotNull(message));
        return json;
    }
}
