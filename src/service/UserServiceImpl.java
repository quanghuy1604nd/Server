/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.UserDAO;
import model.User;

/**
 *
 * @author QuangHuy
 */
public class UserServiceImpl implements IUserService{
    private final UserDAO userDAO = new UserDAO();

    @Override
    public Long getUserIdByStudentCodeAndIP(String studentCode, String ip) {
        return userDAO.findUserIdByStudentCodeAndIP(studentCode, ip);
    }
    
}
