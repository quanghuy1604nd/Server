/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package handler;

import exception.StepErrorException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import utils.Pair;

/**
 *
 * @author QuangHuy
 */
public class ByteRawStreamJudge extends Judge {

    private final InputStream is;
    private final OutputStream os;

    public ByteRawStreamJudge(Socket socket) throws IOException {
        this.is = socket.getInputStream();
        this.os = socket.getOutputStream();
    }
    @Override
    public Pair<String, String> extractClientInfo() throws Exception {
        try {
            byte[] buffer = new byte[1024];
            int bytesRead = is.read(buffer);
            String requestCode = new String(buffer, 0, bytesRead);
            return validate(requestCode);
        } catch (IOException ex) {
            throw new StepErrorException(1, ex);
        }
    }

    @Override
    public int process(String questionCode) throws Exception {
        return this.judge(InputStream.class, OutputStream.class, is, os, questionCode);
    }
}
