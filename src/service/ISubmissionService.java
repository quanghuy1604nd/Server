/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.time.LocalDateTime;

/**
 *
 * @author QuangHuy
 */
public interface ISubmissionService{
    void insertSubmission(Long userExerciseContestId, LocalDateTime createdAt, boolean ac, String srcPath);
}
