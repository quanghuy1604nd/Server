/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;
import model.ExamUserDetail;

/**
 *
 * @author QuangHuy
 */
public class ExamUserDetailDAO extends AbstractDAO {

    private static final String UPDATE_BY_ID_AND_AC 
            = "UPDATE exam_user_exercise_contest SET ac = ? WHERE id = ?";

    private static final String SELECT_BY_USERID_AND_ALIASID
            = """
              SELECT eud.id FROM exam_user_detail eud JOIN exam_user eu ON eud.exam_user_id = eu.id
              WHERE eu.user_id = ? 
              AND eud.alias_id = ? 
              """;
    public ExamUserDetail findByUserIdAndAliasId(UUID userId, UUID aliasId) {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID_AND_AC)) {
            preparedStatement.setObject(1, userId);
            preparedStatement.setObject(2, aliasId);
            ResultSet resultSet = preparedStatement.executeQuery();
            ExamUserDetail examUserDetail = new ExamUserDetail(
                    resultSet.getObject("id", UUID.class), 
                    resultSet.getObject("exam_detail_id", UUID.class), 
                    resultSet.getObject("exam_user_id", UUID.class), 
                    resultSet.getObject("alias_id", UUID.class)
            );
            return examUserDetail;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateExamUserExercise(Long examUserExerciseId, boolean ac) {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID_AND_AC)) {
            preparedStatement.setBoolean(1, ac);
            preparedStatement.setLong(2, examUserExerciseId);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
