/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import util.Pair;

/**
 *
 * @author QuangHuy
 */
public class E1 extends AbstractExercise {

    public E1(DataInputStream dis, DataOutputStream dos) {
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public Pair<String, String> communicate() throws Exception {
        int randNum1 = (int) (Math.random() * 20);
        int randNum2 = (int) (Math.random() * 20);
        int answerSum = randNum1 + randNum2;
        int answerDiff = randNum1 - randNum2;
        int answerMult = randNum1 * randNum2;
        dos.writeInt(randNum1);
        dos.writeInt(randNum2);
        int sum = dis.readInt();
        int diff = dis.readInt();
        int mult = dis.readInt();
        return new Pair<>(answerSum + " " + answerDiff + " " +answerMult, 
                          sum + " " + diff + " " + mult);
    }

}
