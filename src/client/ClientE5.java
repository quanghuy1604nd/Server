/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author QuangHuy
 */
public class ClientE5 {
     public static void main(String[] args) throws IOException {
        Socket socket = new Socket("172.188.19.218", 1605);
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        dos.writeUTF("b20xyzt000;tojbmuP");
        String question = dis.readUTF();
        StringBuilder sb = new StringBuilder(question);
        dos.writeUTF(sb.reverse().toString());
        
        dis.close();
        dos.close();
        socket.close();
    }
}
