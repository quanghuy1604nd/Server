/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;
import model.User;

/**
 *
 * @author QuangHuy
 */
public class UserDAO extends AbstractDAO {

    private static final String SELECT_ID_BY_USERNAME_AND_IPADRESS = "SELECT id FROM \"user\" WHERE username = ? AND ip = ?";
    private static final String SELECT_ID_BY_USERNAME = "SELECT id FROM \"user\" WHERE username = ?";

    public User findByUsernameAndIP(String username, String ip) {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID_BY_USERNAME_AND_IPADRESS);) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, ip);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User(
                        resultSet.getObject("id", UUID.class),
                        resultSet.getString("username"),
                        resultSet.getString("ip_address")
                );
                return user;
            }

        } catch (Exception e) {
            // TODO: Handle
        }
        return null;
    }

    // for testing
    public User findByUsername(String username) {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID_BY_USERNAME);) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User(
                        resultSet.getObject("id", UUID.class),
                        resultSet.getString("username"),
                        resultSet.getString("ip_address")
                );
                return user;
            }
        } catch (Exception e) {
            // TODO: Handle
        }
        return null;
    }

}
