/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author QuangHuy
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static utils.AppConstants.DaoConstants.*;

public abstract class AbstractDAO {

    public Connection getConnection() {
        try {
            Class.forName(CLASS_FOR_NAME);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
