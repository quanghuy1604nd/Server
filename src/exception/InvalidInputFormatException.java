/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

import static utils.AppConstants.ProcessCodeConstants.EXCEPTION;

/**
 *
 * @author QuangHuy
 */
public class InvalidInputFormatException extends BaseException {

    public final int code = EXCEPTION;

    public InvalidInputFormatException(String message) {
        super(message);
    }

    public InvalidInputFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getCode() {
        return this.code;
    }

}
