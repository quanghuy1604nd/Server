/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package question;

import exception.StepErrorException;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author quanghuy
 */
public class Template extends AbstractQuestion{

    public Template(Socket clientSocket, int timeout) throws SocketException {
        super(clientSocket, timeout);
    }

    @Override
    public void initData() {
        // init variable that need to be send
        // Example: private String question
        
    }

    @Override
    public void closeStream() {
        // this method is already implemented in 3 specific stream question
        // don't need to implement this
    }

    @Override
    public void createCommunicationScenario() throws Exception {
        
        actions[0] = () -> {
            try {
                step++;
                
            } catch(Exception ex) {
                throw new StepErrorException(step, ex);
            }
        };
        
        actions[1] = () -> {
            try {
                step++;
                
            } catch(Exception ex) {
                throw new StepErrorException(step, ex);
            }
        };
        // in last actions need to assign this.clientAnswer=value
        // data type of this.clientAnswer is string so remember to convert
        actions[2] = () -> {
            try {
                // last action, variable "step" not increase anymore
                
            } catch(Exception ex) {
                throw new StepErrorException(step, ex);
            }
        };
    }
    
}
