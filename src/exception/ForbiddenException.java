/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

import static util.AppConstants.FORBIDDEN;

/**
 *
 * @author QuangHuy
 */
public class ForbiddenException extends BaseException {
    public final int code = FORBIDDEN;
    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getCode() {
        return this.code;
    }
    
}
