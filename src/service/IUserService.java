/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.User;

/**
 *
 * @author QuangHuy
 */
public interface IUserService {
    Long getUserIdByStudentCodeAndIP(String studentCode, String ip);
}
