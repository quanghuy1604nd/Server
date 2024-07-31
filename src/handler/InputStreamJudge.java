/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author QuangHuy
 */
public class InputStreamJudge extends Judge {
    private final InputStream is;
    private final OutputStream os;
    public InputStreamJudge(Socket socket) throws IOException {
        this.is = socket.getInputStream();
        this.os = socket.getOutputStream();
    }
    public String getRequestCode() throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead = is.read(buffer);
        String requestCode = new String(buffer, 0, bytesRead);
        return requestCode;
    }

    public int process(Long exerciseId) throws Exception {
        return this.judge(InputStream.class, OutputStream.class, is, os, exerciseId);
    }
}
