/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import model.UserExercise;

/**
 *
 * @author QuangHuy
 */
public class UserExerciseDAO extends AbstractDAO {

    private static final String SELECT_USEREXERCISE_BY_USERID_AND_EXERCISEID = "SELECT * FROM user_exercise WHERE user_id = ? AND exercise_id = ?";
    private static final String INSERT_USEREXERCISE_BY_USERID_AND_EXERCISEID = "INSERT INTO user_exercise (user_id, exercise_id, created_at, ac) VALUES(?, ?, ?, ?)";
    private static final String UPDATE_USEREXERCISE_BY_USERID_AND_EXERCISEID_AND_AC = "UPDATE user_exercise SET ac = ? WHERE user_id = ? AND exercise_id = ?";

    public UserExercise getUserExerciseByUserIdAndExerciseId(Long userId, Long exerciseId) {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USEREXERCISE_BY_USERID_AND_EXERCISEID);) {
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, exerciseId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                UserExercise userExercise = new UserExercise();
                userExercise.setId(resultSet.getLong("id"));
                userExercise.setUserId(resultSet.getLong("user_id"));
                userExercise.setExerciseId(resultSet.getLong("exercise_id"));
//                userExercise.setCreatedAt(LocalDateTime resultSet.getString("created_at"));
                return userExercise;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    

    public Long insertUserExercise(Long userId, Long exerciseId, LocalDateTime createdAt, boolean ac) {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USEREXERCISE_BY_USERID_AND_EXERCISEID)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, exerciseId);
            preparedStatement.setObject(3, createdAt); // Sử dụng setObject để lưu LocalDateTime
            preparedStatement.setBoolean(4, ac);
            preparedStatement.executeUpdate();
            return getUserExerciseByUserIdAndExerciseId(userId, exerciseId).getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateUserExercise(Long userId, Long exerciseId, boolean ac) {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USEREXERCISE_BY_USERID_AND_EXERCISEID_AND_AC)) {
            preparedStatement.setBoolean(1, ac);
            preparedStatement.setLong(2, userId);
            preparedStatement.setLong(3, exerciseId);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
