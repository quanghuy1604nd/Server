/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package handler;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 *
 * @author QuangHuy
 */
public class Judge {
    public int judge(Class inputClass, Class outputClass, Object input, Object output, Long exerciseId) throws Exception {
        Class clazz = Class.forName("contest.E" + exerciseId);
        Constructor<?> constructor = clazz.getConstructor(inputClass, outputClass);
        Object instance = constructor.newInstance(input, output);
        Method process = clazz.getMethod("process");
        int result = (int) process.invoke(instance);
        return result;
    }
}
