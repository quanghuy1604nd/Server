/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contest;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketTimeoutException;
import java.util.Random;
import static util.AppConstants.ACCEPTED;
import static util.AppConstants.INVALID_FORMAT_INPUT;
import static util.AppConstants.TIME_OUT;
import static util.AppConstants.WRONG_ANSWER;

/**
 *
 * @author QuangHuy
 */
public class E6 implements IExercise {

    private final InputStream is;
    private final OutputStream os;

    public E6(InputStream is, OutputStream os) {
        this.is = is;
        this.os = os;
    }

    @Override
    public int process() {
        try {
            Random rand = new Random();
            int a = rand.nextInt(20);
            int b = rand.nextInt(10);
            int answer = (int) Math.pow(a, b);
            String question = a + "|" + b;
            os.write(question.getBytes());

            byte[] buffer = new byte[1024];
            int bytesRead = is.read(buffer);
            String clientResponse = new String(buffer, 0, bytesRead);
            System.out.println(clientResponse);

            int response = Integer.parseInt(clientResponse);
            // get client answer
            if (response == answer) {
                return ACCEPTED;
            }
            return WRONG_ANSWER;
        } catch (Exception ex) {
            if (ex instanceof SocketTimeoutException) {
                return TIME_OUT;
            } else if (ex instanceof NumberFormatException) {
                return WRONG_ANSWER;
            }
            return INVALID_FORMAT_INPUT;
        }
    }
}
