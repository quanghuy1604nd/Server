/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import exception.BaseException;
import service.IWebhookService;
import service.WebhookServiceImpl;

/**
 *
 * @author quanghuy
 */
public class MainTest {

    public static void main(String[] args) {
        WebhookServiceImpl webhookService = new WebhookServiceImpl();
//        webhookService.test();
        Runnable action = () -> {
            System.out.println("fdjsakl;fds");
        };
        action.run();
        
    }
}
