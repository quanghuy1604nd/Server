/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

/**
 *
 * @author QuangHuy
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author QuangHuy
 */
public class ClientE6 {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("20.198.242.126", 1604);
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        os.write("b00dccn000;ZvA0lTCn".getBytes());
        byte[] buffer = new byte[1024];
        int bytesRead = is.read(buffer);
        String question = new String(buffer, 0, bytesRead);
        System.out.println(question);
        String[] arr = question.split("\\|");
        int ans = (int) Math.pow(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
        os.write((ans+"").getBytes());

        is.close();
        os.close();
        socket.close();
    }

}
