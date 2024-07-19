/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import static util.AppConstants.*;

/**
 *
 * @author QuangHuy
 */
public class DataExercise extends GeneralExercise {
    protected final DataInputStream dis;
    protected final DataOutputStream dos;

    public DataExercise(Socket socket) throws IOException {
        super(socket); 
        this.dis = new DataInputStream(socket.getInputStream());
        this.dos = new DataOutputStream(socket.getOutputStream());
    }

    public void run() throws SocketException, IOException {
        socket.setSoTimeout(TIME_OUT_DURATION);
        String requestCode = dis.readUTF();
        this.validate(requestCode);
    }
}
