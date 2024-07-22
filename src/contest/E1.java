/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketTimeoutException;
import java.util.Random;
import static util.AppConstants.*;

/**
 *
 * @author QuangHuy
 */
public class E1 implements IExercise {

    private final InputStream is;
    private final OutputStream os;

    public E1(InputStream is, OutputStream os) {
        this.is = is;
        this.os = os;
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
        String question = "";
        for (int i = 0; i < arr.length - 1; i++) {
            question += arr[i] + "|";
        }
        question += arr[3];
        return question;
    }

    public int getAnswer(int[] arr) {
        int serverAns = 0;
        for (int i = 0; i < arr.length; i++) {
            serverAns += arr[i];
        }
        return serverAns;
    }

    @Override
    public int process() {
        try {
            // gen 4 number
            int[] randArr = genRandomArr(4);
            // gen question from 4 number
            String question = genQuestion(randArr);
            os.write(question.getBytes());

            byte[] buffer = new byte[1024];
            int bytesRead = is.read(buffer);
            String clientResponse = new String(buffer, 0, bytesRead);
            
            int response = Integer.parseInt(clientResponse);
            // get client answer
            int serverAns = getAnswer(randArr);
            if(response == serverAns) {
                return ACCEPTED;
            } return WRONG_ANSWER;
        } catch (Exception ex) {
            if(ex instanceof SocketTimeoutException) {
                return TIME_OUT;
            }
            return INVALID_FORMAT_INPUT;
        }
    }
}
