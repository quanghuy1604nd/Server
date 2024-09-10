/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

import static utils.AppConstants.ProcessCodeConstants.INVALID_QUESTION;


/**
 *
 * @author QuangHuy
 */
public class InvalidQuestionException extends BaseException{
    public final int code = INVALID_QUESTION;
    public InvalidQuestionException(String message) {
        super(message);
    }

    public InvalidQuestionException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getCode() {
        return this.code;
    }
    
}
