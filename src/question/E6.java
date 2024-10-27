/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package question;

import exception.StepErrorException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;

/**
 *
 * @author QuangHuy
 */
public class E6 extends ByteRawStreamQuestion {

    private String question;

    private static final int TIME_OUT = 5000;

    public E6(Socket clientSocket) throws IOException {
        super(clientSocket, TIME_OUT);
    }

    @Override
    public void initData() {
        Random rand = new Random();
        int a = rand.nextInt(20);
        int b = rand.nextInt(10);
        this.question = a + "|" + b;
        this.answer = (int) Math.pow(a, b) + "";
    }

    @Override
    public void createCommunicationScenario() throws Exception {
        actions[0] = () -> {
            try {
                step++;
                os.write(question.getBytes());
            } catch (Exception ex) {
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
            } catch (Exception ex) {
                throw new StepErrorException(step, ex);
            }
        };
    }
}
