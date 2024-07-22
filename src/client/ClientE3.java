/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;
import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
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

    String value = "b20dccn112;302";
    writer.write(value);
    writer.write("\n");
    writer.flush();
    String receive = reader.readLine();
    List<String> filtered = Arrays.stream(receive.split(", ")).filter(x -> x.endsWith(".edu")).toList();
    filtered.forEach(System.out::println);
    String result = String.join(", ", filtered);
    System.out.println(result);
    writer.write(result);
    writer.write("\n");
    writer.flush();
    socket.close();
  }
}
