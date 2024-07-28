/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package handler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import static util.AppConstants.INVALID_FORMAT_INPUT;

/**
 *
 * @author QuangHuy
 */
public class DataStreamHandler extends ClientHandler {

    public DataStreamHandler(Socket socket) throws IOException {
        super(socket);
    }

    public void process()  {
        try (DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());) {
            String requestCode = dis.readUTF();
            this.judge(DataInputStream.class, DataOutputStream.class, dis, dos, requestCode);
        } catch (Exception ex) {
            webhookService.sendContestLogs(payload, this.contestId, this.contestUserId, INVALID_FORMAT_INPUT, "Gửi sai định dạng");
        } 
    }
}
