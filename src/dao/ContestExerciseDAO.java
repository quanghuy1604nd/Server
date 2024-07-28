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
public class ContestExerciseDAO extends AbstractDAO{
   private static final String SELECT_EXERCISECONTESTID_BY_EXERCISEID_AND_CONTESTID = "SELECT exercise_contest.id FROM exercise_contest WHERE exercise_contest.exercise_id = ? AND exercise_contest.contest_id = ?";

    public Long findContestExerciseByExerciseIdAndContestId(Long exerciseId, Long contestId) {

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EXERCISECONTESTID_BY_EXERCISEID_AND_CONTESTID);) {
            preparedStatement.setLong(1, exerciseId);
            preparedStatement.setLong(2, contestId);
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
