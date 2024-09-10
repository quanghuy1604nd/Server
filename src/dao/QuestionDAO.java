/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;
import model.Question;

/**
 *
 * @author QuangHuy
 */
public class QuestionDAO extends AbstractDAO {

    private static final String SELECT_BY_ALIASNAME
            = "SELECT q.id, q.code FROM alias a JOIN exam_user_detail eud ON a.id = eud.alias_id "
            + "JOIN exam_detail ed ON eud.exam_detail_id = ed.id "
            + "JOIN question q ON ed.question_id = q.id "
            + "WHERE a.name = ?";
    private static final String SELECT_BY_EXAMDETAILID
            = """
            SELECT q.id, q.code FROM question q JOIN exam_detail ed ON q.id = ed.question_id AND ed.id = ?
            """;

    public Question findByAliasName(String aliasName) {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ALIASNAME);) {
            preparedStatement.setString(1, aliasName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Question(
                        resultSet.getObject("id", UUID.class),
                        resultSet.getString("code")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Question findByExamDetailId(UUID examDetailId) {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_EXAMDETAILID);) {
            preparedStatement.setObject(1, examDetailId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Question(
                        resultSet.getObject("id", UUID.class),
                        resultSet.getString("code")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
