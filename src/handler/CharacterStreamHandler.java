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
import java.util.logging.Level;
import java.util.logging.Logger;
import static util.AppConstants.INVALID_FORMAT_INPUT;

/**
 *
 * @author QuangHuy
 */
public class CharacterStreamHandler extends ClientHandler {

    public CharacterStreamHandler(Socket socket) {
        super(socket);
    }

    public void process() {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
            String requestCode = reader.readLine();
            this.judge(BufferedWriter.class, BufferedReader.class, writer, reader, requestCode);
        } catch (Exception ex) {
            webhookService.sendWebhookLogs(ip, username, alias, INVALID_FORMAT_INPUT, "Gửi sai định dạng");
        }
    }
}
