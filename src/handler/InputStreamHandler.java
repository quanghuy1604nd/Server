/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package handler;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import static util.AppConstants.INVALID_FORMAT_INPUT;

/**
 *
 * @author QuangHuy
 */
public class InputStreamHandler extends ClientHandler{
    
    public InputStreamHandler(Socket socket) {
        super(socket);
    }
    
    public void process() {
        try (InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();) {
             byte[] buffer = new byte[1024];
            int bytesRead = is.read(buffer);
            String requestCode = new String(buffer, 0, bytesRead);
            this.judge(InputStream.class, OutputStream.class, is, os, requestCode);
        } catch (Exception ex) {
            webhookService.sendContestLogs(payload, this.contestId, this.contestUserId, INVALID_FORMAT_INPUT, "Gửi sai định dạng");
        }
    }
}
