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
import utils.Pair;

/**
 *
 * @author QuangHuy
 */
public class E4 extends DataStreamQuestion {

    private int randNum1;
    private int randNum2;

    private static final int TIME_OUT = 5000;

    public E4(Socket clientSocket) throws IOException {
        super(clientSocket, TIME_OUT);
    }

    public static int euclide(int a, int b) {
        if (b == 0) {
            return a;
        }
        return euclide(b, a % b);
    }

    @Override
    public void initData() {
        this.randNum1 = (int) (Math.random() * 20);
        this.randNum2 = (int) (Math.random() * 20);
        int gcd = euclide(randNum1, randNum2);
        int lcm = randNum1 / gcd * randNum2;
        this.answer = gcd + " " + lcm;
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
                int clientGcd = dis.readInt();
                int clientLcm = dis.readInt();
                this.clientAnswer = clientGcd + " " + clientLcm;
            } catch (Exception ex) {
                throw new StepErrorException(step, ex);
            }
        };
    }

}
