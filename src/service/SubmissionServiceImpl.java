/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.SubmissionDAO;
import java.time.LocalDateTime;

/**
 *
 * @author QuangHuy
 */
public class SubmissionServiceImpl implements ISubmissionService{
    private final SubmissionDAO submissionDAO = new SubmissionDAO();

    @Override
    public void insertSubmission(Long userExerciseContestId, LocalDateTime createdAt, boolean ac, String srcPath) {
        submissionDAO.insertSubmission(userExerciseContestId, createdAt, ac, srcPath);
    }
    
}
