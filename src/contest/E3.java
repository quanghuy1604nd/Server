/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Random;
import util.Pair;

/**
 *
 * @author QuangHuy
 */
public class E3 extends AbstractExercise {

    public E3(BufferedWriter writer, BufferedReader reader) {
        this.writer = writer;
        this.reader = reader;
    }

    @Override
    public Pair<String, String> communicate() throws Exception {
        String[] extension = {".vn", ".com", ".edu", ".id"};
        Random random = new Random();
        StringBuilder question = new StringBuilder();
        StringBuilder answer = new StringBuilder();
        int cnt = 5 + random.nextInt(10);

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

        writer.write(question.toString() + "\n");
        writer.flush();
        String response = reader.readLine();
        return new Pair<>(answer.toString(), response);
    }
}
