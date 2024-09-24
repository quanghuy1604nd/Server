/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author QuangHuy
 */
public class ClientE7 {

    public static void main(String[] args) throws IOException {
        String address = "172.188.19.218";
        int port = 1606;

        Socket socket = new Socket(address, port);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String value = "b00test000;q4RRVpR";
        System.out.println(value);
        writer.write(value + "\n");
        writer.flush();
        String question = reader.readLine();
        System.out.println(question);

        StringBuilder as = new StringBuilder();
        for (int i = 0; i < question.length(); i++) {
            char ch = question.charAt(i);
            if (ch != 'u' && ch != 'e' && ch != 'o' && ch != 'a' && ch != 'i') {
                as.append(ch);
            }
        }
        String answer = as.toString();
        System.out.println(answer);

        
        writer.write(answer + "\n");
        writer.flush();
    }
}
