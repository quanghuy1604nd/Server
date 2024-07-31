/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import util.Helper;

/**
 *
 * @author QuangHuy
 */
public class CharacterStreamJudge extends Judge {

    private final BufferedWriter writer;
    private final BufferedReader reader;

    public CharacterStreamJudge(Socket socket) throws IOException {
        this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public String getRequestCode() throws IOException {
        String requestCode = reader.readLine();
        return requestCode;
    }

    public int process(Long exerciseId) throws Exception {
        return this.judge(BufferedWriter.class, BufferedReader.class, writer, reader, exerciseId);
    }
}
