/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author QuangHuy
 */
public class AliasDAO extends AbstractDAO{
    private static final String SELECT_ALIASID_BY_CODE_AND_ACTIVE = "SELECT * FROM alias WHERE code = ? AND active = ?";

    public Long findAliasIdByCode(String code) {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALIASID_BY_CODE_AND_ACTIVE);) {
            preparedStatement.setString(1, code);
            preparedStatement.setBoolean(2, true);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
