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
public class ExamUserDAO extends AbstractDAO {

    private static final String SELECT_BY_USERID_AND_EXAMID = "SELECT id FROM exam_user WHERE user_id=? AND exam_id=?";

    public Long findByUserIdAndContestId(Long userId, Long contestId) {
        try (Connection connection = getConnection(); 
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_USERID_AND_EXAMID);) {
            preparedStatement.setLong(1, userId);
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
