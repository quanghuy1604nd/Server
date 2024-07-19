/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import handler.ClientHandler;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static util.AppConstants.*;

/**
 *
 * @author QuangHuy
 */
public class TcpServer {

    private final ServerSocket server;
    private final ExecutorService pool;

    public TcpServer() throws IOException {
        this.server = new ServerSocket(SERVER_PORT);
        this.pool = Executors.newFixedThreadPool(MAX_CONNECTION);
    }
    public TcpServer(Integer ServerPort) throws IOException {
        this.server = new ServerSocket(ServerPort);
        this.pool = Executors.newFixedThreadPool(MAX_CONNECTION);
    }

    public void run() {
        try {
            System.out.println("Server is ready at port " + this.server.getLocalPort() + "...");

            while (true) {
                Socket conn = this.server.accept();
                ClientHandler handler = new ClientHandler(conn);
                pool.execute(handler);
            }

        } catch (IOException ex) {
            // TODO: handle
        }
    }

    public void closeClientSocket(Socket socket) throws IOException {
        if (!socket.isClosed()) {
            socket.close();
        }
    }

    public void shutdown() throws IOException {
        if (!this.server.isClosed()) {
            try (this.server) {
                this.pool.shutdown();
            }
        }
    }
}
