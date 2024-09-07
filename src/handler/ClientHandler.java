/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package handler;

/**
 *
 * @author QuangHuy
 */
import dao.AliasDAO;
import dao.ExamDAO;
import dao.ExamDetailDAO;
import dao.QuestionDAO;
import dao.SubmissionDAO;
import dao.ExamUserDAO;
import dao.ExamUserDetailDAO;
import dao.UserDAO;
import exception.InvalidInputFormatException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.UUID;
import payload.Payload;
import service.IWebhookService;
import service.WebhookServiceImpl;
import static utils.AppConstants.*;
import static utils.AppConstants.PortConfiguration.*;
import static utils.Helper.getStackTraceException;
import utils.Pair;

public class ClientHandler implements Runnable {
    protected final Socket socket;
    protected ExamDetailDAO examDetailDAO;
    protected SubmissionDAO submissionDAO;
    protected UserDAO userDAO;
    protected QuestionDAO questionDAO;
    protected ExamDAO examDAO;
    protected ExamUserDAO examUserDAO;
    protected ExamUserDetailDAO examUserDetailDAO;
    protected AliasDAO aliasDAO;
    protected IWebhookService webhookService;

    protected int type;
    protected Payload payload;
    protected Long test;

    protected UUID examId;
    protected UUID examUserExerciseId;
    protected UUID examUserId;
    protected UUID examExerciseId;
    protected UUID userId;
    protected UUID exerciseId;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        this.payload = new Payload();
        this.payload.setIpAddress(((InetSocketAddress) socket.getRemoteSocketAddress()).getAddress().getHostAddress());
        this.userDAO = new UserDAO();
        this.questionDAO = new QuestionDAO();
        this.aliasDAO = new AliasDAO();
        this.webhookService = new WebhookServiceImpl();
    }

    public void initDAO() {
        this.examDetailDAO = new ExamDetailDAO();
        this.submissionDAO = new SubmissionDAO();
        this.examDAO = new ExamDAO();
        this.examUserDAO = new ExamUserDAO();
        this.examUserDetailDAO = new ExamUserDetailDAO();
    }
    
    @Override
    public void run() {
        String message;
        int code;
        try {
            socket.setSoTimeout(TIME_OUT_DURATION);
            Judge judge = null;
            switch (socket.getLocalPort()) {
                
                case INPUT_STREAM_PORT -> {
                    judge = new ByteRawStreamJudge(this.socket);
                }
                case DATA_STREAM_PORT -> {
                    judge = new DataStreamJudge(this.socket);
                }
                case CHARACTER_STREAM_PORT -> {
                    judge = new CharacterStreamJudge(this.socket);
                }
                case OBJECT_STREAM_PORT -> {
                    judge = new CharacterStreamJudge(this.socket);
                }
                default -> {
                    throw new InvalidInputFormatException("s");
                }
            }
            Pair<String, String> clientInfo = judge.extractClientInfo();
            this.payload.setUsername(clientInfo.getKey());
            this.payload.setAlias(clientInfo.getValue());
            String questionCode = questionDAO.findQuestionCodeByAliasName(clientInfo.getValue());
            System.out.println(questionCode);
            int result = judge.process(questionCode);
            System.out.println(result);
        } catch(InvocationTargetException ex) {
            message = getStackTraceException(ex.getCause());
            System.out.println("|--------------------------------------------------------|");
            System.out.println(message);
        } catch (Exception ex) { // Bắt tất cả các ngoại lệ và xử lý riêng biệt bằng instanceof
//            message = ex.getStackTrace();
//            if (ex instanceof InvocationTargetException) {
//                message = helper.getStackTrace(ex);
//            } else {
//                message = helper.getStackTrace(ex);
//            }
            message = getStackTraceException(ex);
            System.out.println(message);

        } finally {
            shutdown();
        }
    }

    public void updateContestExerciseStatus(int code) {
//        if (code == ACCEPTED) {
//            examUserDetailDAO.updateExamUserExercise(this.examUserExerciseId, code == ACCEPTED);
//        }
//        submissionDAO.insertExamSubmission(examUserExerciseId, LocalDateTime.now(), code == ACCEPTED, "");
//        webhookService.sendExamLogs(payload, this.examId, this.examUserId, code, "");
//        webhookService.sendRequestToUpdateExamRank(payload, this.examId, this.userId);
    }

    public boolean isValidQuestion() {
//        userId = userDAO.findByUsernameAndIP(payload.getUsername(), payload.getIp());
////        userId = userDAO.findUserIdByStudentCode(payload.getUsername());
//        if (userId == null) {
//            String message = "Chưa đăng kí hoặc IP không trùng khớp!";
//            webhookService.sendExamLogs(payload, this.examId, examUserId, FORBIDDEN, message);
//            shutdown();
//            return false;
//        }
//
//        this.examDetailDAO.findExamExerciseByExerciseIdAndExamId(userId, examId);
//        if (this.examUserId == null) {
//            String message = "Không được tham gia cuộc thi này!";
//            webhookService.sendExamLogs(payload, this.examId, this.examUserId, FORBIDDEN, message);
//            shutdown();
//            return false;
//        }
//        exerciseId = exerciseDAO.findExerciseByAliasCode(payload.getAlias());
//        if (exerciseId == null) {
//            String message = "Mã bài tập không hợp lệ!";
////            webhookService.sendContestLogs(payload, this.contestId, this.examId FORBIDDEN, message);
//            shutdown();
//            return false;
//        }
//        this.examExerciseId = this.examDetailDAO.findExamExerciseByExerciseIdAndExamId(this.exerciseId, this.examId);
//        if (this.examUserExerciseId == null) {
//            String message = "Mã bài tập không hợp lệ!";
//            webhookService.sendExamLogs(payload, this.examId, this.examUserId, FORBIDDEN, message);
//            shutdown();
//            return false;
//        }
//        this.examUserExerciseId = this.examUserDetailDAO.findExamUserExerciseByAliasCode(payload.getAlias());
//        if (this.examUserExerciseId == null) {
//            String message = "Mã bài tập không hợp lệ!";
//            webhookService.sendExamLogs(payload, this.examId, this.examUserId, REQUEST_WRONG_EXERCISE, message);
//            shutdown();
//            return false;
//        }
        return true;
    }

   

    public void shutdown() {
        try {
            if (!this.socket.isClosed()) {
                this.socket.close();
            }
        } catch (IOException ex) {
            // TODO: handle
        }
    }
}
