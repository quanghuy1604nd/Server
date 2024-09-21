package tcpserver_thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author QuangHuy
 */
class ClientThread extends Thread {

    private int calcSum(String question) {
        String[] arr = question.split("\\|");
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += Integer.parseInt(arr[i]);
        }
        return sum;
    }

    @Override
    public void run() {
        String studentCode = "b00TEST000";
        String questionCode = "iG7N6RD";
        String serverAddress = "172.188.19.218";
        int serverPort = 1605;
        try (Socket socket = new Socket(serverAddress, serverPort); DataInputStream dis = new DataInputStream(socket.getInputStream()); DataOutputStream dos = new DataOutputStream(socket.getOutputStream());) {

            String requestCode = studentCode + ";" + questionCode;
            // send request with studentcode and question code to server
            dos.writeUTF(requestCode);
            int a = dis.readInt();
            int b = dis.readInt();
            System.out.println(a + " 1" + b);

            dos.writeInt(a + b + 1);
            dos.writeInt(a - b);
            dos.writeInt(a * b);
        } catch (IOException ex) {
            Logger.getLogger(MultiThreadTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("---------End--------");
    }
}

public class MultiThreadTest {

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            Thread t = new ClientThread();
            t.start();
        }
    }
}
