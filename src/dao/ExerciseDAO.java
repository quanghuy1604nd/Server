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
public class ExerciseDAO extends DAO {

    private static final String SELECT_EXERCISE_BY_ID = "SELECT * FROM exercise WHERE id = ?";
    private static final String SELECT_EXERCISEID_BY_ALIAS_CODE = "SELECT exercise.id FROM exercise INNER JOIN alias  ON exercise.id = alias.exercise_id AND alias.code = ?";

    public Exercise findExerciseById(Long id) {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EXERCISE_BY_ID);) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Exercise exercise = new Exercise();
                exercise.setId(resultSet.getInt("id"));
                return exercise;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Long findExerciseIdByAliasCode(String code) {
        try (Connection connection = getConnection(); 
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EXERCISEID_BY_ALIAS_CODE);) {
            preparedStatement.setString(1, code);
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
