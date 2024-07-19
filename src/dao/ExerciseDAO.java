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
    private static final String SELECT_EXERCISE_BY_ID= "SELECT * FROM exercise WHERE id = ?";

    public Exercise findExerciseById(Long id) {
        try (Connection connection = getConnection(); 
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EXERCISE_BY_ID);) {
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

}
