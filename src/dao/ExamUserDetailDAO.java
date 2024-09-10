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

    private static final String SELECT_BY_ID
            = "SELECT id, exam_detail_id, exam_user_id FROM exam_user_detail eud WHERE id=?";

    public ExamUserDetail findById(UUID aliasId) {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setObject(1, aliasId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ExamUserDetail examUserDetail = new ExamUserDetail(
                        resultSet.getObject("id", UUID.class),
                        resultSet.getObject("exam_detail_id", UUID.class),
                        resultSet.getObject("exam_user_id", UUID.class)
                );
                return examUserDetail;
            }
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
