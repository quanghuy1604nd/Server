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
public class ContestUserDAO extends AbstractDAO {

    private static final String SELECT_USERCONTESTID_BY_USERID_AND_CONTESTID = "SELECT user_contest.id FROM contest_user WHERE user_id=? AND contest_id=?";

    public Long findContestUserByUserIdAndContestId(Long userId, Long contestId) {
        try (Connection connection = getConnection(); 
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERCONTESTID_BY_USERID_AND_CONTESTID);) {
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
