/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.time.LocalDateTime;
import model.UserExercise;

/**
 *
 * @author QuangHuy
 */
public interface IUserExerciseService {
    UserExercise getUserExerciseByUserIdAndExerciseId(Long userId, Long exerciseId);
    Long insertUserExercise(Long userId, Long exerciseId, LocalDateTime createdAt, boolean ac);
    void updateUserExercise(Long userId, Long exerciseId, boolean ac);
}
