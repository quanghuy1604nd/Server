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
    String address = "172.188.19.218";
    int port = 1606;

    Socket socket = new Socket(address, port);
    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    Long start = System.currentTimeMillis();
    String value = "b00test000;DgXx3FW";
    writer.write(value);
    writer.write("\n");
    writer.flush();
    Long x1 = System.currentTimeMillis();
        System.out.println("1 " + (x1 - start));
    String receive = reader.readLine();
    Long x2 = System.currentTimeMillis();
        System.out.println("2 " + (x2 - x1));
    String[] arr = receive.split(", ");
    StringBuilder sb = new StringBuilder();
    for(String x : arr) {
        if(x.endsWith(".edu")) {
        System.out.println(x);
            sb.append(x);
            sb.append(", ");
        }
    }
        System.out.println(sb);
    String result = sb.length() == 0 ? "" : sb.substring(0, sb.length() - 2);
    Long x3 = System.currentTimeMillis();
    writer.write(result);
    writer.write("\n");
    writer.flush();
    socket.close();
  }
}
