/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Exercise;

/**
 *
 * @author QuangHuy
 */
public class ExerciseDAO extends AbstractDAO {

    private static final String SELECT_EXERCISE_BY_ALASCODE = "SELECT e.id FROM exercise e INNER JOIN alias a ON e.id = a.exercise_id AND a.code = ?";

    public Long findExerciseByAliasCode(String aliasCode) {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EXERCISE_BY_ALASCODE);) {
            preparedStatement.setString(1, aliasCode);
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
