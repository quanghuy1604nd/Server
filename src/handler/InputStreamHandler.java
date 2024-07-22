/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package handler;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.Socket;

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
            System.out.println(requestCode);
            if (isValid(requestCode)) {
                Class clazz = Class.forName("contest.E" + this.exerciseId);
                Constructor<?> constructor = clazz.getConstructor(InputStream.class, OutputStream.class);
                Object instance = constructor.newInstance(is, os);
                Method process = clazz.getMethod("process");
                int result = (int) process.invoke(instance);
                this.updateExerciseContestStatus(result);
            }
        } catch (Exception ex) {
            this.webhookService.sendWebhookLogs("Invalid data");
        }
    }
}
