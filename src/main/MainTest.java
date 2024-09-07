/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import exception.BaseException;

/**
 *
 * @author quanghuy
 */
public class MainTest {
    private static void heh() throws BaseException {
        System.out.println("dfsa");
        throw new RuntimeException("fdsa");
        
    }
    public static void main(String[] args) {
        Runnable action = () -> {
            System.out.println("fdjsakl;fds");
        };
        action.run();
        
    }
}
