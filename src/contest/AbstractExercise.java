/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import static util.AppConstants.*;
import util.Pair;

/**
 *
 * @author QuangHuy
 */
public abstract class AbstractExercise {

    protected DataInputStream dis;
    protected DataOutputStream dos;
    protected InputStream is;
    protected OutputStream os;
    protected BufferedWriter writer;
    protected BufferedReader reader;

    public int process() throws Exception {
        Pair<String, String> result = communicate();
        if (result.getKey().equals(result.getValue())) {
            return ACCEPTED;
        }
        return WRONG_ANSWER;
    }

    public void closeStream() {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(AbstractExercise.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (writer != null) {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(AbstractExercise.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (dis != null) {
            try {
                dis.close();
            } catch (IOException ex) {
                Logger.getLogger(AbstractExercise.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (dos != null) {
            try {
                dos.close();
            } catch (IOException ex) {
                Logger.getLogger(AbstractExercise.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (is != null) {
            try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(AbstractExercise.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (os != null) {
            try {
                os.close();
            } catch (IOException ex) {
                Logger.getLogger(AbstractExercise.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // override this method for each exercise
    abstract Pair<String, String> communicate() throws Exception;
}
