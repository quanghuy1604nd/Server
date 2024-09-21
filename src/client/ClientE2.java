/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author QuangHuy
 */
public class ClientE2 implements Runnable{
    private final String studentCode;
    private final String questionCode;
    private final String serverAddress;
    private final int serverPort;
    private Socket client;

    private int calcSum(String question) {
        String[] arr = question.split("\\|");
        int sum = 0;
        for (String x : arr) {
            sum += Integer.parseInt(x);
        }
        return sum;
    }
    public ClientE2(String studentCode, String questionCode, String serverAddress, int serverPort) {
        this.studentCode = studentCode;
        this.questionCode = questionCode;
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    @Override
    public void run() {
        try {
            this.client = new Socket(this.serverAddress, this.serverPort);
            InputStream is = this.client.getInputStream();
            OutputStream os = this.client.getOutputStream();

            String requestCode = studentCode + ";" + questionCode;
            // send request with studentcode and question code to server
            os.write(requestCode.getBytes());
            // get question from server
             byte[] buffer = new byte[1024];
            int bytesRead = is.read(buffer);
            String question = new String(buffer, 0, bytesRead);
            int sum = calcSum(question);
            System.out.println("question: " + question);

            // send back to server
            os.write((sum+"").getBytes());
            is.close();
            os.close();
            System.out.println("---------End--------");
        } catch(IOException ex) {
            // TODO: handle
        }
//        catch (InterruptedException ex) {
//            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
//        }
        finally {
            shutdown();
        }
    }
    public void shutdown() {
        try {
            if (this.client != null && !this.client.isClosed()) {
                this.client.close();
            }
        } catch (IOException ex) {
            // TODO: handle
        }
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        String studentCode = "b20DCCN000";
        String questionCode = "4GLWUnJ";
        String serverAddress = "172.188.19.218";
        int serverPort = 1604;
        ClientE2 client = new ClientE2(studentCode, questionCode, serverAddress, serverPort);
        client.run();
    }

}

