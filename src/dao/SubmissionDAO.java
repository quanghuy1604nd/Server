/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 *
 * @author QuangHuy
 */
public class SubmissionDAO extends DAO{

    private static final String INSERT_SUBMISSON_BY_USEREXERCISECONTESTID = "INSERT INTO submission (user_exercise_contest_id, created_at, ac, src_path) VALUES(?, ?, ?, ?)";

    public void insertSubmission(Long userExerciseContestId, LocalDateTime createdAt, boolean ac, String srcPath) {
        try (Connection connection = getConnection(); 
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SUBMISSON_BY_USEREXERCISECONTESTID);) {
            preparedStatement.setLong(1, userExerciseContestId);
            preparedStatement.setObject(2, createdAt);
            preparedStatement.setBoolean(3, ac);
            preparedStatement.setString(4, srcPath);
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
