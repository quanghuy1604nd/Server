/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package question;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author quanghuy
 */
public abstract class ByteRawStreamQuestion extends AbstractQuestion{
    
    protected InputStream is;
    protected OutputStream os;
    
    public ByteRawStreamQuestion(Socket clientSocket, int timeout) throws IOException {
        super(clientSocket, timeout);
        this.is = clientSocket.getInputStream();
        this.os = clientSocket.getOutputStream();
    }
    
    @Override
    public void closeStream() {
        if (is != null) {
            try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(AbstractQuestion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (os != null) {
            try {
                os.close();
            } catch (IOException ex) {
                Logger.getLogger(AbstractQuestion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}