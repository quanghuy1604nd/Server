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
public class QuestionDAO extends AbstractDAO {

    private static final String SELECT_BY_ALIASNAME
            = "SELECT q.code FROM alias a JOIN exam_user_detail eud ON a.id = eud.alias_id "
            + "JOIN exam_detail ed ON eud.exam_detail_id = ed.id "
            + "JOIN question q ON ed.question_id = q.id "
            + "WHERE a.name = ?";
    
    public String findQuestionCodeByAliasName(String aliasName) {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ALIASNAME);) {
            preparedStatement.setString(1, aliasName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("code");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
