/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;
import model.Alias;

/**
 *
 * @author QuangHuy
 */
public class AliasDAO extends AbstractDAO{
    private static final String SELECT_ID_BY_NAME_AND_ACTIVE = "SELECT * FROM alias WHERE name = ? AND active = ?";

    public Alias findByNameAndActive(String name, boolean active) {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID_BY_NAME_AND_ACTIVE);) {
            preparedStatement.setString(1, name);
            preparedStatement.setBoolean(2, active);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Alias(
                        resultSet.getObject("id", UUID.class),
                        resultSet.getString("name"),
                        resultSet.getObject("exam_user_detail_id", UUID.class)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
