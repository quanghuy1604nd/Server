/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package handler;

/**
 *
 * @author QuangHuy
 */

import contest.Ex1;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientHandler implements Runnable {
    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            Ex1 ex1 = new Ex1(socket);
            ex1.run();
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void shutdown() {
        if (!this.socket.isClosed()) {
            try {
                this.socket.close();
            } catch (IOException ex) {
                // TODO: handle
            }
        }
    }
}
