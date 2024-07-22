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
public class UserContestDAO extends DAO {

    private static final String SELECT_USERCONTESTID_BY_USERID_AND_CONTESTID = "SELECT user_contest.id FROM user_contest INNER JOIN \"user\"  ON \"user\".id = user_contest.user_id AND \"user\".username = ? AND user_contest.contest_id = ?";

    public Long findUserContestIdByUsernameAndContestId(String username, Long contestId) {

        contestId = 1L;

        try (Connection connection = getConnection(); 
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERCONTESTID_BY_USERID_AND_CONTESTID);) {
            preparedStatement.setString(1, username);
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
