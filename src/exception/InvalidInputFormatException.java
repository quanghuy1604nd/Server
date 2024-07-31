/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

import static util.AppConstants.INVALID_FORMAT_INPUT;

/**
 *
 * @author QuangHuy
 */
public class InvalidInputFormatException extends BaseException {

    public final int code = INVALID_FORMAT_INPUT;

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
