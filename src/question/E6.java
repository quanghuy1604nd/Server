/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package question;

import exception.StepErrorException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

/**
 *
 * @author QuangHuy
 */
public class E6 extends ByteRawStreamQuestion {
    private String question;
    
    public E6(InputStream is, OutputStream os) {
        this.is = is;
        this.os = os;
    }
    
    @Override
    void initData() {
        Random rand = new Random();
        int a = rand.nextInt(20);
        int b = rand.nextInt(10);
        this.question = a + "|" + b;
        this.answer = (int) Math.pow(a, b) +"";
    }

    @Override
    void createCommunicationScenario() throws Exception {
        actions[0] = () -> {
            try {
                step++;
                os.write(question.getBytes());
            } catch(Exception ex) {
                throw new StepErrorException(step, ex);
            }
        };
        
        actions[1] = () -> {
            try {
                byte[] buffer = new byte[1024];
                int bytesRead = is.read(buffer);
                String clientResponse = new String(buffer, 0, bytesRead);
                int x = Integer.parseInt(clientResponse);
                this.clientAnswer = clientResponse;
            } catch(Exception ex) {
                throw new StepErrorException(step, ex);
            }
        };
    }
}
