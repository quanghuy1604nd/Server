/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package handler;

import exception.MalformedRequestCodeException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import static utils.Helper.isValidateRequestCode;
import utils.Pair;

/**
 *
 * @author QuangHuy
 */
public abstract class Judge {

    public Pair<String, String> validate(String requestCode) {
        if (isValidateRequestCode(requestCode)) {
            String[] code = requestCode.split(";");
            String username = code[0].toLowerCase();
            String alias = code[1];
            return new Pair<>(username, alias);
        } else {
            throw new MalformedRequestCodeException(String.format("request code %s is not invalid", requestCode));
        }
    }

    public boolean judge(Class inputClass, Class outputClass, Object input, Object output, String questionCode) throws Exception {
        Class clazz = Class.forName("question." + questionCode);
        Constructor<?> constructor = clazz.getConstructor(inputClass, outputClass);
        Object instance = constructor.newInstance(input, output);
        Method process = clazz.getMethod("process");
        boolean result = (boolean) process.invoke(instance);
        return result;
    }
    abstract Pair<String, String> extractClientInfo() throws Exception;
    abstract boolean process(String questionCode) throws Exception;
}
