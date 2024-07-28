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
public class ContestUserExerciseDAO extends AbstractDAO {

    private static final String UPDATE_CONTESTUSEREXERCISE_BY_CONTESTUSEREXERCISEID_AND_AC = "UPDATE user_exercise_contest SET ac = ? WHERE id = ?";
    private static final String SELECT_CONTESTUSEREXERCISE_BY_ALASCODE = "SELECT c FROM contest_user_exercise c INNER JOIN alias a ON c.alias_id = a.id AND a.code = ?";

    public Long findContestUserExerciseByAliasCode(String aliasCode) {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CONTESTUSEREXERCISE_BY_ALASCODE);) {
            preparedStatement.setString(1, aliasCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                resultSet.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateContestUserExercise(Long userExerciseContestId, boolean ac) {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CONTESTUSEREXERCISE_BY_CONTESTUSEREXERCISEID_AND_AC)) {
            preparedStatement.setBoolean(1, ac);
            preparedStatement.setLong(2, userExerciseContestId);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
