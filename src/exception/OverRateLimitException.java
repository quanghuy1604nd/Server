/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;

/**
 *
 * @author quanghuy
 */
public class OverRateLimitException extends RuntimeException{

    public OverRateLimitException(String message) {
        super(message);
    }
    
}
