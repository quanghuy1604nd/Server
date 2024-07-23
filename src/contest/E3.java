/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Random;
import static util.AppConstants.*;

/**
 *
 * @author QuangHuy
 */
public class E3 implements IExercise {

    private final BufferedWriter writer;
    private final BufferedReader reader;

    public E3(BufferedWriter writer, BufferedReader reader) {
        this.writer = writer;
        this.reader = reader;
    }

    @Override
    public int process() {
        String[] extenstion = {".vn", ".com", ".edu", ".id"};
        Random random = new Random();
        StringBuilder question = new StringBuilder();
        StringBuilder answer = new StringBuilder();
        int len = 10 + random.nextInt(10);
        for (int cnt = 0; cnt < len; cnt++) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < random.nextInt(26); i++) {
                char x = (char) (i + 'a');
                if (random.nextBoolean()) {
                    x = (char) (i + 'A');
                }
                sb.append(x);
            }
            int randId = random.nextInt(extenstion.length);
            sb.append(extenstion[randId]);
            if (randId == 2) {
                answer.append(sb);
                answer.append(", ");
            }
            question.append(sb);
            question.append(", ");
        }
        System.out.println(question);
        try {
            writer.write(question.substring(0, question.length() - 2));
            writer.flush();
            String response = reader.readLine();
            System.out.println(response);
            if (response.equals(answer.substring(0, answer.length() - 2))) {
                return ACCEPTED;
            }
            return WRONG_ANSWER;
        } catch (Exception ex) {
            if (ex instanceof SocketTimeoutException) {
                return TIME_OUT;
            }
            return INVALID_FORMAT_INPUT;
        }
    }

}
