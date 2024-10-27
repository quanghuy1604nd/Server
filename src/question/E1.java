/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package question;

import exception.StepErrorException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author QuangHuy
 */
public class E1 extends DataStreamQuestion {

    private int randNum1;
    private int randNum2;
    private static final int TIME_OUT = 5000;
    public E1(Socket clientSocket) throws IOException {
        super(clientSocket, TIME_OUT);
    }

    @Override
    public void initData() {
        randNum1 = (int) (Math.random() * 20);
        randNum2 = (int) (Math.random() * 20);
        int answerSum = randNum1 + randNum2;
        int answerDiff = randNum1 - randNum2;
        int answerMult = randNum1 * randNum2;
        this.answer = answerSum + " " + answerDiff + " " + answerMult;
    }

    @Override
    public void createCommunicationScenario() throws Exception {
        actions[0] = () -> {
            try {
                step++;
                dos.writeInt(randNum1);
                dos.writeInt(randNum2);
            } catch (Exception ex) {
                throw new StepErrorException(step, ex);
            }
        };

        actions[1] = () -> {
            try {
                int sum = dis.readInt();
                int diff = dis.readInt();
                int mult = dis.readInt();
                this.clientAnswer = sum + " " + diff + " " + mult;
            } catch (Exception ex) {
                throw new StepErrorException(step, ex);
            }
        };
    }

}
