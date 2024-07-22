/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;

/**
 *
 * @author QuangHuy
 */
public class UserExerciseContestDAO extends DAO {

    private static final String SELECT_USEREXERCISECONTESTID_BY_USERCONTESTID_AND_EXERCISECONTESTID_AND_ALIASID = "SELECT user_exercise_contest.id FROM user_exercise_contest WHERE user_contest_id = ? AND exercise_contest_id = ? AND alias_id = ?";
    private static final String UPDATE_USEREXERCISECONTEST_BY_USERCONTESTID_AND_EXERCISECONTESTID_AND_AC = "UPDATE user_exercise_contest SET ac = ? WHERE id = ?";

    public Long findUserExerciseContestByUserContestIdAndExerciseContestId(Long userContestId, Long exerciseContestId, Long aliasId) {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USEREXERCISECONTESTID_BY_USERCONTESTID_AND_EXERCISECONTESTID_AND_ALIASID);) {
            preparedStatement.setLong(1, userContestId);
            preparedStatement.setLong(2, exerciseContestId);
            preparedStatement.setLong(3, aliasId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void updateUserExerciseContest(Long userExerciseContestId,  boolean ac) {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USEREXERCISECONTEST_BY_USERCONTESTID_AND_EXERCISECONTESTID_AND_AC)) {
            preparedStatement.setBoolean(1, ac);
            preparedStatement.setLong(2, userExerciseContestId);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
