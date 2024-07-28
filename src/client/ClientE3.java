/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;
import java.io.*;
import java.net.Socket;
/**
 *
 * @author QuangHuy
 */
public class ClientE3 {
    public static void main(String[] args) throws IOException {
    String address = "localhost";
    int port = 1606;

    Socket socket = new Socket(address, port);
    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    String value = "b20dccn100;86ZIrDRn";
    writer.write(value);
    writer.write("\n");
    writer.flush();
    String receive = reader.readLine();
    String[] arr = receive.split(", ");
    StringBuilder sb = new StringBuilder();
    for(String x : arr) {
        if(x.endsWith(".edu")) {
            sb.append(x);
            sb.append(", ");
        }
    }
        System.out.println(sb);
    String result = sb.length() == 0 ? "" : sb.substring(0, sb.length() - 2);
    writer.write(result);
    writer.write("\n");
    writer.flush();
    socket.close();
  }
}
