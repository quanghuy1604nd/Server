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
public class ClientE4 {
     public static int euclide(int a, int b) {
        if(b == 0) return a;
        return euclide(b, a%b);
    }

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("172.188.19.218", 1605);
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        dos.writeUTF("b20dccn000;rxiXvHW");
        int a = dis.readInt();
        int b = dis.readInt();
        System.out.println(a + " " + b);
        dos.writeInt(euclide(a, b));
        dos.writeInt(a * b / euclide(a, b));
        
        dis.close();
        dos.close();
        socket.close();
    }
}
