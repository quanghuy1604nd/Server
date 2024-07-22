/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package handler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            System.out.println(requestCode);
            if (isValid(requestCode)) {
                Class clazz = Class.forName("contest.E" + this.exerciseId);
                Constructor<?> constructor = clazz.getConstructor(DataInputStream.class, DataOutputStream.class);
                Object instance = constructor.newInstance(dis, dos);
                Method process = clazz.getMethod("process");
                int result = (int) process.invoke(instance);
                this.updateExerciseContestStatus(result);
            }
        } catch (Exception ex) {
            this.webhookService.sendWebhookLogs("Invalid data");
        }
    }
}
