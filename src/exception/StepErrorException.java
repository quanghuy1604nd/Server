/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

import static utils.Helper.getStackTraceException;

/**
 *
 * @author quanghuy
 */
public class StepErrorException extends RuntimeException {

    private final int step;

    public StepErrorException(int step, Exception exception) {
        super("Error on step: " + step + "\n" + getStackTraceException(exception));
        this.step = step;
    }
}
