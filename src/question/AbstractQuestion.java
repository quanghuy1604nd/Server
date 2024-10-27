package question;

import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author QuangHuy
 */
public abstract class AbstractQuestion {
    protected final Socket clientSocket;
    protected final int timeout;

    protected int step = 1;
    protected String answer;
    protected String clientAnswer;
    protected Runnable[] actions = new Runnable[200];

    public AbstractQuestion(Socket clientSocket, int timeout) throws SocketException {
        this.clientSocket = clientSocket;
        this.timeout = timeout;
        this.clientSocket.setSoTimeout(this.timeout);
    }
    
    public boolean process() throws Exception {
        initData();
        createCommunicationScenario();
        runActions();
        closeStream();
        return this.answer.equals(this.clientAnswer);
    }

    protected void runActions() {
        for (int i = 0; i < step; i++) {
            actions[i].run();
        }
    }
    // override this method for initiate data before communicating
    public abstract void initData();
    
    // override this method to close stream
    public abstract void closeStream();

    // override this method to create senario
    public abstract void createCommunicationScenario() throws Exception;
}