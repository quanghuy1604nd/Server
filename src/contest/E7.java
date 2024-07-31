/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.SocketTimeoutException;
import java.util.Random;
import static util.AppConstants.ACCEPTED;
import static util.AppConstants.INVALID_FORMAT_INPUT;
import static util.AppConstants.TIME_OUT;
import static util.AppConstants.WRONG_ANSWER;
import util.Pair;

/**
 *
 * @author QuangHuy
 */
public class E7 extends AbstractExercise {

    public E7(BufferedWriter writer, BufferedReader reader) {
        this.writer = writer;
        this.reader = reader;
    }
    private static String src = "abcdefghijklmnopqrstuvwxyz";

    @Override
    public Pair<String, String> communicate() throws Exception {
        Random rand = new Random();
        int len = 20 + rand.nextInt(40);
        StringBuilder sb = new StringBuilder();
        StringBuilder as = new StringBuilder();
        for (int i = 0; i < len; i++) {
            char ch = src.charAt(rand.nextInt(src.length()));
            if (ch != 'u' && ch != 'e' && ch != 'o' && ch != 'a' && ch != 'i') {
                as.append(ch);
            }
            sb.append(ch);
        }
        String question = sb.toString();
        String answer = as.toString();
        writer.write(question + "\n");
        writer.flush();
        String response = reader.readLine();
        return new Pair<>(response, answer);
    }

}
