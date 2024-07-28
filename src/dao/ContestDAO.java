/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import model.Contest;

/**
 *
 * @author QuangHuy
 */
public class ContestDAO extends AbstractDAO {

    private static final String SELECT_CONTEST_BY_ID = "SELECT * FROM contest WHERE id = ?";

    public Contest findContestById(Long id) {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CONTEST_BY_ID);) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Contest contest = new Contest();
                contest.setId(resultSet.getLong("id"));
                contest.setStartTime(resultSet.getObject("start_time", LocalDateTime.class));
                contest.setEndTime(resultSet.getObject("end_time", LocalDateTime.class));
                return contest;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
