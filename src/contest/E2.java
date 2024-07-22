/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static util.AppConstants.*;

/**
 *
 * @author QuangHuy
 */
public class E2 implements IExercise {

    private final DataInputStream dis;
    private final DataOutputStream dos;

    public E2(DataInputStream dis, DataOutputStream dos) {
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public int process() {
        try {
            int randNum1 = (int) (Math.random() * 20);
            int randNum2 = (int) (Math.random() * 20);
            int answer = randNum1 + randNum2;
            dos.writeInt(randNum1);
            dos.writeInt(randNum2);
            int clientResponse = dis.readInt();
            if(answer == clientResponse) {
                return ACCEPTED;
            } return WRONG_ANSWER;
        } catch (Exception ex) {
            if(ex instanceof SocketTimeoutException) {
                return TIME_OUT;
            }
            return INVALID_FORMAT_INPUT;
        }
    }

}
