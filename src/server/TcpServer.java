package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import static utils.AppConstants.AppConfiguration.MAX_CONNECTION;
import static utils.AppConstants.PortConfiguration.*;
import java.util.logging.Logger;

/**
 * TcpServer class to handle multiple types of streams.
 */
public class TcpServer {
    private static final Logger logger = Logger.getLogger(TcpServer.class.getName());

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
            logger.log(Level.INFO, "InputStream Server is ready at port {0}...", this.inputStreamServer.getLocalPort());
            logger.log(Level.INFO, "DataStream Server is ready at port {0}...", this.dataStreamServer.getLocalPort());
            logger.log(Level.INFO, "CharacterStream Server is ready at port {0}...", this.charStreamServer.getLocalPort());
            logger.log(Level.INFO, "ObjectStream Server is ready at port {0}...", this.objStreamServer.getLocalPort());
            filter.scheduleFilter();
            // Start a new thread for each server socket
            new Thread(() -> listenToPort(this.inputStreamServer)).start();
            new Thread(() -> listenToPort(this.dataStreamServer)).start();
            new Thread(() -> listenToPort(this.charStreamServer)).start();
            new Thread(() -> listenToPort(this.objStreamServer)).start();

        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        }
    }

    private void listenToPort(ServerSocket serverSocket) {
        while (true) {
            try {
                Socket conn = serverSocket.accept();
                filter.doFilter(conn, pool);
            } catch (IOException ex) {
                logger.log(Level.SEVERE, ex.getMessage());
            }
        }
    }

    public void closeClientSocket(Socket socket) throws IOException {
        if (!socket.isClosed()) {
            socket.close();
        }
    }

    public void shutdown() throws IOException {
        logger.log(Level.INFO, "(shutdown)");
        try {
            if (!this.inputStreamServer.isClosed()) this.inputStreamServer.close();
            if (!this.dataStreamServer.isClosed()) this.dataStreamServer.close();
            if (!this.charStreamServer.isClosed()) this.charStreamServer.close();
            if (!this.objStreamServer.isClosed()) this.objStreamServer.close();
            this.pool.shutdown();
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "(shutdown) {0}}", ex.getMessage());
        }
    }
}
