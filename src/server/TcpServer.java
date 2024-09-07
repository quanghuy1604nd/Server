package server;

import handler.ClientHandler;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static utils.AppConstants.*;
import static utils.AppConstants.AppConfiguration.MAX_CONNECTION;
import static utils.AppConstants.PortConfiguration.*;

/**
 * TcpServer class to handle multiple types of streams.
 */
public class TcpServer {

    private final ServerSocket inputStreamServer;
    private final ServerSocket dataStreamServer;
    private final ServerSocket charStreamServer;
    private final ServerSocket objStreamServer;
    private final Filter filter;

    private final ExecutorService pool;

    public TcpServer() throws IOException {
        this.inputStreamServer = new ServerSocket(INPUT_STREAM_PORT);
        this.dataStreamServer = new ServerSocket(DATA_STREAM_PORT);
        this.charStreamServer = new ServerSocket(CHARACTER_STREAM_PORT);
        this.objStreamServer = new ServerSocket(OBJECT_STREAM_PORT);
        this.filter = new Filter();
        this.pool = Executors.newFixedThreadPool(MAX_CONNECTION);
    }
    

    public void run() {
        try {
            System.out.println("InputStream Server is ready at port " + this.inputStreamServer.getLocalPort() + "...");
            System.out.println("DataStream Server is ready at port " + this.dataStreamServer.getLocalPort() + "...");
            System.out.println("CharacterStream Server is ready at port " + this.charStreamServer.getLocalPort() + "...");
            System.out.println("ObjectStream Server is ready at port " + this.objStreamServer.getLocalPort() + "...");
            filter.scheduleFilter();
            // Start a new thread for each server socket
            new Thread(() -> listenToPort(this.inputStreamServer)).start();
            new Thread(() -> listenToPort(this.dataStreamServer)).start();
            new Thread(() -> listenToPort(this.charStreamServer)).start();
            new Thread(() -> listenToPort(this.objStreamServer)).start();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void listenToPort(ServerSocket serverSocket) {
        while (true) {
            try {
                Socket conn = serverSocket.accept();
                filter.doFilter(conn, pool);
            } catch (IOException ex) {
                System.out.println("Handle failed");
            }
        }
    }

    public void closeClientSocket(Socket socket) throws IOException {
        if (!socket.isClosed()) {
            socket.close();
        }
    }

    public void shutdown() throws IOException {
        try {
            if (!this.inputStreamServer.isClosed()) this.inputStreamServer.close();
            if (!this.dataStreamServer.isClosed()) this.dataStreamServer.close();
            if (!this.charStreamServer.isClosed()) this.charStreamServer.close();
            if (!this.objStreamServer.isClosed()) this.objStreamServer.close();
            this.pool.shutdown();
        } catch (IOException ex) {
            // Handle
        }
    }
}
