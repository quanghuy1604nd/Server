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
public class PracticeUserExerciseDAO extends AbstractDAO {

    private static final String SELECT_PRACTICEUSEREXERCISE_BY_ALASCODE = "SELECT p.id FROM practice_user_exercise p INNER JOIN alias a ON p.alias_id = a.id AND a.code = ?";
    private static final String UPDATE_PRACTICEUSEREXERCISE_BY_PRACTICEUSEREXERCISEID_AND_AC = "UPDATE practice_user_exercise SET ac = ? WHERE id = ?";

    public Long findPracticeUserExerciseByAliasCode(String aliasCode) {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRACTICEUSEREXERCISE_BY_ALASCODE);) {
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

    public void updateContestUserExercise(Long practiceUserExerciseId, boolean ac) {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRACTICEUSEREXERCISE_BY_PRACTICEUSEREXERCISEID_AND_AC)) {
            preparedStatement.setBoolean(1, ac);
            preparedStatement.setLong(2, practiceUserExerciseId);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
