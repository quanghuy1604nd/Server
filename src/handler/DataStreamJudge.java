/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package handler;

import exception.StepErrorException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.UUID;
import utils.Pair;

/**
 *
 * @author QuangHuy
 */
public class DataStreamJudge extends Judge {

    private final DataInputStream dis;
    private final DataOutputStream dos;

    public DataStreamJudge(Socket socket) throws IOException {
        this.dis = new DataInputStream(socket.getInputStream());
        this.dos = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public Pair<String, String> extractClientInfo() throws Exception {
        try {
            String requestCode = dis.readUTF();
            return validate(requestCode);
        } catch (Exception ex) {
            throw new StepErrorException(1, ex);
        }

    }
    @Override
    public int process(String questionCode) throws Exception {
        return this.judge(DataInputStream.class, DataOutputStream.class, dis, dos, questionCode);
    }
}
