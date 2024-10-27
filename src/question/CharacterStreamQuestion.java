/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package question;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author quanghuy
 */
public abstract class CharacterStreamQuestion extends AbstractQuestion {
    
    protected BufferedWriter writer;
    protected BufferedReader reader;

    public CharacterStreamQuestion(Socket clientSocket, int timeout) throws IOException {
        super(clientSocket, timeout);
        this.writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        this.reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }
    
    @Override
    public void closeStream() {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(AbstractQuestion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (writer != null) {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(AbstractQuestion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}