/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;
import model.Exam;

/**
 *
 * @author QuangHuy
 */
public class ExamDAO extends AbstractDAO {
    private static final String SELECT_BY_ID = "SELECT * FROM exam WHERE id = ?";
    public Exam findById(UUID id) {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);) {
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Exam exam = new Exam();
                exam.setId(resultSet.getObject("id", UUID.class));
                exam.setStartTime(resultSet.getObject("start_time", OffsetDateTime.class).toInstant());
                exam.setEndTime(resultSet.getObject("end_time", OffsetDateTime.class).toInstant());
                return exam;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        ExamDAO dao = new ExamDAO();
        Exam exam = dao.findById(UUID.fromString("808cf378-339b-487c-8991-a5a199279406"));
        System.out.println(exam.getStartTime().isAfter(Instant.now()));
        System.out.println(exam.getStartTime());
        System.out.println(exam.getEndTime().isAfter(Instant.now()));
        System.out.println(Instant.now());
    }
}
