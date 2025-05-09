/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package question;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author quanghuy
 */
public abstract class DataStreamQuestion extends AbstractQuestion {
    protected DataInputStream dis;
    protected DataOutputStream dos;

    public DataStreamQuestion(Socket clientSocket, int timeout) throws IOException {
        super(clientSocket, timeout);
        this.dis = new DataInputStream(clientSocket.getInputStream());
        this.dos = new DataOutputStream(clientSocket.getOutputStream());
    }
    
    
    @Override
    public void closeStream() {
        if (dis != null) {
            try {
                dis.close();
            } catch (IOException ex) {
                Logger.getLogger(AbstractQuestion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (dos != null) {
            try {
                dos.close();
            } catch (IOException ex) {
                Logger.getLogger(AbstractQuestion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}