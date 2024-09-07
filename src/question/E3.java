/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package question;

import exception.StepErrorException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Random;
import utils.Pair;

/**
 *
 * @author QuangHuy
 */
public class E3 extends CharacterStreamQuestion {

    private String question;

    public E3(BufferedWriter writer, BufferedReader reader) {
        this.writer = writer;
        this.reader = reader;
    }

    @Override
    void initData() {
        String[] extension = {".vn", ".com", ".edu", ".id"};
        Random random = new Random();
        StringBuilder questionSb = new StringBuilder();
        StringBuilder answerSb = new StringBuilder();
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
                if (answerSb.length() > 0) {
                    answerSb.append(", ");
                }
                answerSb.append(sb);
            }
            if (questionSb.length() > 0) {
                questionSb.append(", ");
            }
            questionSb.append(sb);
        }
        this.question = questionSb.toString();
        this.answer = answerSb.toString();
    }

    @Override
    public void createCommunicationScenario() {
        actions[0] = () -> {
            try {
                step++;
                writer.write(question + "\n");
                writer.flush();
            } catch (Exception ex) {
                throw new StepErrorException(step, ex);
            }
        };
        
        actions[1] = () -> {
            try {
                this.clientAnswer = reader.readLine();
            } catch(Exception ex) {
                throw new StepErrorException(step, ex);
            }
        };
    }

}
