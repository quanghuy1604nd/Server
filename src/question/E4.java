/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package question;

import exception.StepErrorException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import utils.Pair;

/**
 *
 * @author QuangHuy
 */
public class E4 extends DataStreamQuestion {

    private int randNum1;
    private int randNum2;

    public E4(DataInputStream dis, DataOutputStream dos) {
        this.dis = dis;
        this.dos = dos;
    }

    public static int euclide(int a, int b) {
        if (b == 0) {
            return a;
        }
        return euclide(b, a % b);
    }

    @Override
    void initData() {
        this.randNum1 = (int) (Math.random() * 20);
        this.randNum2 = (int) (Math.random() * 20);
        int gcd = euclide(randNum1, randNum2);
        int lcm = randNum1 / gcd * randNum2;
        this.answer = gcd + " " + lcm;
    }

    @Override
    void createCommunicationScenario() throws Exception {
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
