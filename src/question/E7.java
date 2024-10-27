/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package question;

import exception.StepErrorException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;
import static utils.AppConstants.RandomConstants.ALL_LOWER_LETTERS;
import utils.Pair;

/**
 *
 * @author QuangHuy
 */
public class E7 extends CharacterStreamQuestion {

    private String question;

    private static final int TIME_OUT = 5000;

    public E7(Socket clientSocket) throws IOException {
        super(clientSocket, TIME_OUT);
    }

    @Override
    public void initData() {
        var tmp = generate();
        this.question = tmp.getKey();
        this.answer = tmp.getValue();
    }

    @Override
    public void createCommunicationScenario() throws Exception {
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
            } catch (Exception ex) {
                throw new StepErrorException(step, ex);
            }
        };
    }

    private Pair<String, String> generate() {
        Random rand = new Random();
        int len = 20 + rand.nextInt(40);
        StringBuilder sb = new StringBuilder();
        StringBuilder as = new StringBuilder();
        for (int i = 0; i < len; i++) {
            char ch = ALL_LOWER_LETTERS.charAt(rand.nextInt(ALL_LOWER_LETTERS.length()));
            if (ch != 'u' && ch != 'e' && ch != 'o' && ch != 'a' && ch != 'i') {
                as.append(ch);
            }
            sb.append(ch);
        }
        return new Pair<>(sb.toString(), as.toString());
    }

}
