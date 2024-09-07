/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

import static utils.AppConstants.ProcessCodeConstants.REQUEST_WRONG_EXERCISE;


/**
 *
 * @author QuangHuy
 */
public class WrongExerciseException extends BaseException{
    public final int code = REQUEST_WRONG_EXERCISE;
    public WrongExerciseException(String message) {
        super(message);
    }

    public WrongExerciseException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getCode() {
        return this.code;
    }
    
}
