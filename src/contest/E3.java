/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
        String[] extension = {".vn", ".com", ".edu", ".id"};
        Random random = new Random();
        StringBuilder question = new StringBuilder();
        StringBuilder answer = new StringBuilder();
        int cnt = 1 + random.nextInt(5);

        for (int x = 0; x < cnt; x++) {
            int len = 1 + random.nextInt(10);  // Ensure at least one character
            StringBuilder sb = new StringBuilder(len); // Approximate length
            for (int i = 0; i < len; i++) {
                char ch = (char) ('a' + random.nextInt(26));
                if (random.nextBoolean()) {
                    ch = Character.toUpperCase(ch);
                }
                sb.append(ch);
            }
            int randId = random.nextInt(extension.length);
            sb.append(extension[randId]);
            if (randId == 2) {
                if (answer.length() > 0) {
                    answer.append(", ");
                }
                answer.append(sb);
            }
            if (question.length() > 0) {
                question.append(", ");
            }
            question.append(sb);
        }

        try {
            writer.write(question.toString());
            writer.flush();
            String response = reader.readLine();
            if (response != null && response.equals(question.toString())) {
                return ACCEPTED;
            }
            return WRONG_ANSWER;
        } catch (SocketTimeoutException e) {
            return TIME_OUT;
        } catch (Exception e) {
            return INVALID_FORMAT_INPUT;
        }
    }
}
