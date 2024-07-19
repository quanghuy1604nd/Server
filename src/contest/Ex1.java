/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.UserExercise;
import static util.AppConstants.*;

/**
 *
 * @author QuangHuy
 */
public class Ex1 extends DataExercise {

    private static final Long QUESTION_CODE = 1L;

    public Ex1(Socket socket) throws IOException {
        super(socket);
    }

    public int[] genRandomArr(int sz) {
        int[] a = new int[sz];
        Random rand = new Random();

        for (int i = 0; i < sz; i++) {
            a[i] = rand.nextInt(30);
        }
        return a;
    }

    public String genQuestion(int[] arr) {
        String question = "";
        for (int i = 0; i < arr.length - 1; i++) {
            question += arr[i] + "|";
        }
        question += arr[3];
        return question;
    }

    public int getAnswer(int[] arr) {
        int serverAns = 0;
        for (int i = 0; i < arr.length; i++) {
            serverAns += arr[i];
        }
        return serverAns;
    }

    @Override
    public void process(String studentCode, String ip, String questionCode) {
        try {
            // gen 4 number
            int[] randArr = genRandomArr(4);
            // gen question from 4 number
            String question = genQuestion(randArr);
            dos.writeUTF(question);
            // get client answer
            int clientAns = dis.readInt();
            int serverAns = getAnswer(randArr);
            boolean status = clientAns == serverAns;
            // Log
            String message = String.format("%s %d: %s: question: %s server answer: %d, client answer: %d, status: %s",
                    this.socket.getInetAddress(), this.socket.getPort(), studentCode, question, clientAns, serverAns, clientAns == serverAns);
            webhookService.sendWebhookLogs(message);
            if (status) {
                userExerciseContestDAO.updateUserExerciseContest(userExerciseContestId, status);
            }
            submissionDAO.insertSubmission(userExerciseContestId, LocalDateTime.now(), status, "");
            webhookService.sendWebhookUpdateScoreBoard(userId);
            shutdown();
        } catch (IOException ex) {
            Logger.getLogger(Ex1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
