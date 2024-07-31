/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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
public class E5 extends AbstractExercise {

    public E5(DataInputStream dis, DataOutputStream dos) {
        this.dis = dis;
        this.dos = dos;
    }
    private static String src = "0123456789abcdefghijklmnopqrstuvwxyz";

    public String generate() {
        Random rand = new Random();
        int len = 10 + rand.nextInt(20);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(src.charAt(rand.nextInt(src.length())));
        }
        return sb.toString();
    }

    @Override
    public Pair<String, String> communicate() throws Exception {
        String question = generate();
        dos.writeUTF(question);
        String clientResponse = dis.readUTF();
        StringBuilder sb = new StringBuilder(clientResponse);
        return new Pair<>(question, sb.reverse().toString());
    }

}
