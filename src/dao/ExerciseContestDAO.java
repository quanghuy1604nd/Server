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
public class ExerciseContestDAO extends DAO{
   private static final String SELECT_EXERCISECONTESTID_BY_EXERCISEID_AND_CONTESTID = "SELECT exercise_contest.id FROM exercise_contest INNER JOIN exercise  ON exercise.id = exercise_contest.exercise_id AND exercise.alias = ? AND exercise_contest.contest_id = ?";

    public Long findUserContestIdByUserIdAndContestId(String exerciseAlias, Long contestId) {

        contestId = 1L;

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EXERCISECONTESTID_BY_EXERCISEID_AND_CONTESTID);) {
            preparedStatement.setString(1, exerciseAlias);
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
