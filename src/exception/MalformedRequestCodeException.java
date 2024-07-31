/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

import static util.AppConstants.MALFORMED_REQUEST_CODE;

/**
 *
 * @author QuangHuy
 */
public class MalformedRequestCodeException extends BaseException{
    public final int code = MALFORMED_REQUEST_CODE;
    public MalformedRequestCodeException(String message) {
        super(message);
    }

    public MalformedRequestCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getCode() {
        return this.code;
    }
    
}
