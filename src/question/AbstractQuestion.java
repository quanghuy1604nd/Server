/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package question;

/**
 *
 * @author QuangHuy
 */
public abstract class AbstractQuestion {

    protected int step = 1;
    protected String answer;
    protected String clientAnswer;
    protected Runnable[] actions = new Runnable[200];

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
    abstract void initData();
    
    // override this method to close stream
    abstract void closeStream();

    // override this method to create senario
    abstract void createCommunicationScenario() throws Exception;
}
