/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 *
 * @author QuangHuy
 */
public class CharacterStreamHandler extends ClientHandler {

    public CharacterStreamHandler(Socket socket) {
        super(socket);
    }

    public void process() {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); 
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
            String requestCode = reader.readLine();
            System.out.println(requestCode);
            if (isValid(requestCode)) {
                Class clazz = Class.forName("contest.E" + this.exerciseId);
                Constructor<?> constructor = clazz.getConstructor(BufferedWriter.class, BufferedReader.class);
                Object instance = constructor.newInstance(writer, reader);
                Method process = clazz.getMethod("process");
                int result = (int) process.invoke(instance);
                this.updateExerciseContestStatus(result);
            }
        } catch (Exception ex) {
            this.webhookService.sendWebhookLogs("Invalid data");
        }
    }
}
