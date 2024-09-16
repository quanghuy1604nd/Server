/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

//import handler.ClientHandler;
import handler.ClientHandler;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import static utils.AppConstants.AppConfiguration.MAX_CONNECTION_PER_MINUTE;
import static utils.AppConstants.AppConfiguration.WAITING_TIME;

/**
 *
 * @author QuangHuy
 */
public class Filter {

    private final Map<String, Integer> requestCounts;
    private final Map<String, Integer> remainDeniedTime;

    public Filter() throws IOException {
        this.requestCounts = new ConcurrentHashMap<>();
        this.remainDeniedTime = new ConcurrentHashMap<>();
    }

    public void scheduleFilter() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            this.requestCounts.clear();
            Set<Map.Entry<String, Integer>> entrySet = this.remainDeniedTime.entrySet();
            for (Map.Entry<String, Integer> entry : entrySet) {
                String key = entry.getKey();
                int remain = entry.getValue();
                if (remain > 1) {
                    this.remainDeniedTime.put(entry.getKey(), entry.getValue() - 1);
                } else {
                    this.remainDeniedTime.remove(key);
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
    }
    
    public void doFilter(Socket client, ExecutorService executorService) throws IOException {
        String clientIP = ((InetSocketAddress) client.getRemoteSocketAddress()).getAddress().getHostAddress();
        if (this.remainDeniedTime.containsKey(clientIP)) {
            this.closeClientSocket(client);
        } else {
            int count = this.requestCounts.getOrDefault(clientIP, 0);
            this.requestCounts.put(clientIP, count + 1);
            if (isSpam(clientIP, count + 1)) {
                String message = String.format("IP: %s reach limit %d request per %s -> Denied, Wait for %d %s\n",
                        clientIP, MAX_CONNECTION_PER_MINUTE, TimeUnit.SECONDS, WAITING_TIME, TimeUnit.SECONDS);
                this.closeClientSocket(client);
            } else {
                ClientHandler handler = new ClientHandler(client);
                executorService.execute(handler);
            }
        }
    }


    public void closeClientSocket(Socket socket) throws IOException {
        if (!socket.isClosed()) {
            socket.close();
        }
    }

    public boolean isSpam(String clientIP, int requestCounts) {
        if (requestCounts > MAX_CONNECTION_PER_MINUTE) {
            this.remainDeniedTime.put(clientIP, WAITING_TIME);
            return true;
        }
        return false;
    }
}
