/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

import java.io.IOException;
import java.net.SocketTimeoutException;
import static utils.AppConstants.ProcessCodeConstants.INVALID_INPUT_FORMAT;
import static utils.AppConstants.ProcessCodeConstants.OTHER;
import static utils.AppConstants.ProcessCodeConstants.TIME_OUT;
import static utils.Helper.getStackTraceException;

/**
 *
 * @author quanghuy
 */
public class StepErrorException extends RuntimeException {

    private final int step;
    private int processCode;
    
    public StepErrorException(int step, Exception exception) {
        super("Error on step: " + step + "\n" + getStackTraceException(exception));
        this.step = step;
        if (exception instanceof SocketTimeoutException) {
            this.processCode = TIME_OUT;
        } else if(exception instanceof IOException) {
            this.processCode = INVALID_INPUT_FORMAT;
        } else {
            this.processCode = OTHER;
        }
    }

    public int getProcessCode() {
        return processCode;
    }
    
}
