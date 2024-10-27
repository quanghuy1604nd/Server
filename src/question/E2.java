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
public class E2 extends ByteRawStreamQuestion {
    private String question;

      private static final int TIME_OUT = 5000;
    public E2(Socket clientSocket) throws IOException {
        super(clientSocket, TIME_OUT);
    }

    @Override
    public void initData() {
        int[] arr = genRandomArr(4);
        this.question = genQuestion(arr);
        this.answer = getAnswer(arr) + "";
    }

    public int[] genRandomArr(int sz) {
        int[] a = new int[sz];
        Random rand = new Random();

        for (int i = 0; i < sz; i++) {
            a[i] = rand.nextInt(30);
        }
        return a;
    }

    public String genQuestion(int[] arr) {
        String q = "";
        for (int i = 0; i < arr.length - 1; i++) {
            q += arr[i] + "|";
        }
        q += arr[3];
        return q;
    }

    public int getAnswer(int[] arr) {
        int a = 0;
        for (int i = 0; i < arr.length; i++) {
            a += arr[i];
        }
        return a;
    }

    @Override
    public void createCommunicationScenario() throws Exception {
        actions[0] = () -> {
            try {
                step++;
                os.write(this.question.getBytes());
            } catch (Exception ex) {
                throw new StepErrorException(step, ex);
            }

        };

        actions[1] = () -> {
            try {
                byte[] buffer = new byte[1024];
                int bytesRead = is.read(buffer);
                String clientResponse = new String(buffer, 0, bytesRead);
                int num = Integer.parseInt(clientResponse);
                this.clientAnswer = num + "";
            } catch (Exception ex) {
                throw new StepErrorException(step, ex);
            }
        };
    }

}
