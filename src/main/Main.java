/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.IOException;
import server.TcpServer;

/**
 *
 * @author QuangHuy
 */
public class Main {

    public static void main(String[] args) throws IOException {
        String portStr = System.getenv("port");
        if (portStr != null) {
            try {
                int port = Integer.parseInt(portStr);
                TcpServer tcpServer = new TcpServer(port);
                tcpServer.run();
            } catch (NumberFormatException e) {
                System.out.println("Invalid port");
            }
        } else {
            System.out.println("Run server with default port 806");
            TcpServer tcpServer = new TcpServer();
            tcpServer.run();
        }
    }
}
