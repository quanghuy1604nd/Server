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

public class DAO {

    private static final String jdbcUrl = "jdbc:postgresql://localhost:5432/ltm_demo";
    private static final String classForName = "org.postgresql.Driver";
    private static final String jdbcUsername = "postgres";
    private static final String jdbcPassword = "1234";

    public Connection getConnection() {
        try {
            Class.forName(classForName);
            Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
