/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.UserExerciseDAO;
import java.time.LocalDateTime;
import model.UserExercise;

/**
 *
 * @author QuangHuy
 */
public class UserExerciseServiceImpl implements IUserExerciseService {
    private final UserExerciseDAO userExerciseDAO = new UserExerciseDAO();
    @Override
    public UserExercise getUserExerciseByUserIdAndExerciseId(Long userId, Long exerciseId) {
        return userExerciseDAO.getUserExerciseByUserIdAndExerciseId(userId, exerciseId);
    }
    @Override
    public Long insertUserExercise(Long userId, Long exerciseId, LocalDateTime createdAt, boolean ac) {
        return userExerciseDAO.insertUserExercise(userId, exerciseId, createdAt, ac);
    }
    @Override
    public void updateUserExercise(Long userId, Long exerciseId, boolean ac) {
        userExerciseDAO.updateUserExercise(userId, exerciseId, ac);
    }
}
