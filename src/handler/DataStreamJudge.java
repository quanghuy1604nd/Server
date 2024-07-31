/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package handler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author QuangHuy
 */
public class DataStreamJudge extends Judge{
    private final DataInputStream dis;
    private final DataOutputStream dos;

    public DataStreamJudge(Socket socket) throws IOException {
        this.dis = new DataInputStream(socket.getInputStream());
        this.dos = new DataOutputStream(socket.getOutputStream());
    }
    
    public String getRequestCode() throws IOException {
        String requestCode = dis.readUTF();
        return requestCode;
    }

    public int process(Long exerciseId) throws Exception {
        return this.judge(DataInputStream.class, DataOutputStream.class, dis, dos, exerciseId);
    }
}
