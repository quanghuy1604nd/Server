/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package question;

import exception.StepErrorException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Random;
/**
 *
 * @author QuangHuy
 */
public class E5 extends DataStreamQuestion {

    private String question;

    public E5(DataInputStream dis, DataOutputStream dos) {
        this.dis = dis;
        this.dos = dos;
    }
    private static String src = "0123456789abcdefghijklmnopqrstuvwxyz";

    @Override
    void initData() {
        this.question = generate();
        this.answer = new StringBuilder(question).reverse().toString();
    }

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
    public void createCommunicationScenario() throws Exception {
        actions[0] = () -> {
            try {
                step++;
                dos.writeUTF(question);
            } catch (Exception ex) {
                throw new StepErrorException(step, ex);
            }
        };

        actions[1] = () -> {
            try {
                this.clientAnswer = dis.readUTF();
            } catch (Exception ex) {
                throw new StepErrorException(step, ex);
            }
        };
    }
}
