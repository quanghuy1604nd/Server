/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.SocketTimeoutException;
import static util.AppConstants.ACCEPTED;
import static util.AppConstants.INVALID_FORMAT_INPUT;
import static util.AppConstants.TIME_OUT;
import static util.AppConstants.WRONG_ANSWER;

/**
 *
 * @author QuangHuy
 */
public class E4 implements IExercise {

    private final DataInputStream dis;
    private final DataOutputStream dos;

    public E4(DataInputStream dis, DataOutputStream dos) {
        this.dis = dis;
        this.dos = dos;
    }
    public static int euclide(int a, int b) {
        if(b == 0) return a;
        return euclide(b, a%b);
    }

    @Override
    public int process() {
        try {
            int randNum1 = (int) (Math.random() * 20);
            int randNum2 = (int) (Math.random() * 20);
            int gcd = euclide(randNum1, randNum2);
            int lcm = randNum1 / gcd * randNum2;
            dos.writeInt(randNum1);
            dos.writeInt(randNum2);
            int clientGcd = dis.readInt();
            int clientLcm = dis.readInt();
            if(gcd == clientGcd && lcm == clientLcm ) {
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
