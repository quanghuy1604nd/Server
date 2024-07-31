/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contest;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketTimeoutException;
import java.util.Random;
import util.Pair;

/**
 *
 * @author QuangHuy
 */
public class E6 extends AbstractExercise {

    public E6(InputStream is, OutputStream os) {
        this.is = is;
        this.os = os;
    }

    @Override
    public Pair<String, String> communicate() throws Exception {
            Random rand = new Random();
            int a = rand.nextInt(20);
            int b = rand.nextInt(10);
            int answer = (int) Math.pow(a, b);
            String question = a + "|" + b;
            os.write(question.getBytes());
            byte[] buffer = new byte[1024];
            int bytesRead = is.read(buffer);
            String clientResponse = new String(buffer, 0, bytesRead);
            int response = Integer.parseInt(clientResponse);
            return new Pair<>(answer+"", response +"");
    }
}
