/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;
/**
 *
 * @author QuangHuy
 */
public class UserDAO extends DAO {

    private static final String SELECT_ID_BY_STUDENT_CODE_AND_IP= "SELECT id FROM \"user\" WHERE username = ? AND ip = ?";
    private static final String SELECT_ID_BY_STUDENT_CODE= "SELECT id FROM \"user\" WHERE username = ?";

    public Long findUserIdByStudentCodeAndIP(String studentCode, String ip) {
        try (Connection connection = getConnection(); 
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID_BY_STUDENT_CODE_AND_IP);) {
            preparedStatement.setString(1, studentCode);
            preparedStatement.setString(2, ip);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
               return resultSet.getLong("id");
            }
        } catch (Exception e) {
            // TODO: Handle
        }
        return null;
    }
    
    // for testing
    public Long findUserIdByStudentCode(String studentCode) {
        try (Connection connection = getConnection(); 
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID_BY_STUDENT_CODE);) {
            preparedStatement.setString(1, studentCode);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
               return resultSet.getLong("id");
            }
        } catch (Exception e) {
            // TODO: Handle
        }
        return null;
    }

}
