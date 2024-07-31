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
import dao.ContestDAO;
import dao.ContestExerciseDAO;
import dao.ExerciseDAO;
import dao.ContestSubmissionDAO;
import dao.ContestUserDAO;
import dao.UserDAO;
import dao.ContestUserExerciseDAO;
import dao.PracticeSubmissionDAO;
import dao.PracticeUserExerciseDAO;
import exception.ForbiddenException;
import exception.InvalidInputFormatException;
import exception.MalformedRequestCodeException;
import exception.WrongExerciseException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import model.Contest;
import payload.Payload;
import service.IWebhookService;
import service.WebhookServiceImpl;
import static util.AppConstants.*;
import util.Helper;

public class ClientHandler implements Runnable {

    protected final Socket socket;
    protected ContestExerciseDAO contestExerciseDAO;
    protected ContestSubmissionDAO contestSubmissionDAO;
    protected UserDAO userDAO;
    protected ExerciseDAO exerciseDAO;
    protected ContestDAO contestDAO;
    protected ContestUserDAO contestUserDAO;
    protected ContestUserExerciseDAO contestUserExerciseDAO;
    protected AliasDAO aliasDAO;
    protected PracticeUserExerciseDAO practiceUserExerciseDAO;
    protected PracticeSubmissionDAO practiceSubmissionDAO;
    protected IWebhookService webhookService;

    protected Helper helper;

    protected int type;
    protected Payload payload;
    protected Long test;

    protected Long contestId;
    protected Long contestUserExerciseId;
    protected Long contestUserId;
    protected Long ContestExerciseId;
    protected Long userId;
    protected Long exerciseId;

    protected Long practiceUserExerciseId;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        this.payload = new Payload();
        this.helper = new Helper();
        this.payload.setIp(((InetSocketAddress) socket.getRemoteSocketAddress()).getAddress().getHostAddress());
        this.userDAO = new UserDAO();
        this.exerciseDAO = new ExerciseDAO();
        this.aliasDAO = new AliasDAO();
        this.webhookService = new WebhookServiceImpl();
    }

    public void initContestDAO() {
        this.contestExerciseDAO = new ContestExerciseDAO();
        this.contestSubmissionDAO = new ContestSubmissionDAO();
        this.contestDAO = new ContestDAO();
        this.contestUserDAO = new ContestUserDAO();
        this.contestUserExerciseDAO = new ContestUserExerciseDAO();
    }

    public void initPracticeDAO() {
        this.practiceSubmissionDAO = new PracticeSubmissionDAO();
        this.practiceUserExerciseDAO = new PracticeUserExerciseDAO();
    }

    @Override
    public void run() {
        String message = "";
        int code;
        try {
            socket.setSoTimeout(TIME_OUT_DURATION);
//            webhookService.sendPracticeLogs(payload, CONNECT_SUCCESS, "");
            int result = -1;
            switch (socket.getLocalPort()) {
                case INPUT_STREAM_PORT -> {
                    InputStreamJudge inputStreamJudge = new InputStreamJudge(this.socket);
                    String requestCode = inputStreamJudge.getRequestCode();
                    validate(requestCode);
                    result = inputStreamJudge.process(this.exerciseId);
                }
                case DATA_STREAM_PORT -> {
                    DataStreamJudge dataStreamJudge = new DataStreamJudge(this.socket);
                    String requestCode = dataStreamJudge.getRequestCode();
                    validate(requestCode);
                    result = dataStreamJudge.process(this.exerciseId);
                }
                case CHARACTER_STREAM_PORT -> {
                    CharacterStreamJudge characterStreamJudge = new CharacterStreamJudge(this.socket);
                    String requestCode = characterStreamJudge.getRequestCode();
                    validate(requestCode);
                    result = characterStreamJudge.process(this.exerciseId);
                }
                case OBJECT_STREAM_PORT -> {
                }
                default -> {
                    throw new InvalidInputFormatException("s");
                }
            }
            if (result != -1) {
                updatePracticeExerciseStatus(result);
            }

        } catch (Exception ex) { // Bắt tất cả các ngoại lệ và xử lý riêng biệt bằng instanceof
            message = ex.getMessage();
            if (ex instanceof SocketTimeoutException) {
                code = TIME_OUT;
            } else if (ex instanceof InvalidInputFormatException invalidInputFormatException) {
                code = invalidInputFormatException.getCode();
            } else if (ex instanceof ForbiddenException forbiddenException) {
                code = forbiddenException.getCode();
            } else if (ex instanceof WrongExerciseException wrongExerciseException) {
                code = wrongExerciseException.getCode();
            } else if (ex instanceof MalformedRequestCodeException malformedRequestCodeException) {
                code = malformedRequestCodeException.getCode();
                message = ex.getMessage();
            } else if (ex instanceof IOException) {
                code = INVALID_FORMAT_INPUT;
                message = ex.getMessage();
            } else if (ex instanceof InvocationTargetException) {
                // lỗi trong quá trình giao tiếp
                Throwable cause = ex.getCause();
                if (cause instanceof SocketTimeoutException) {
                    code = TIME_OUT;
                } else {
                    code = INVALID_FORMAT_INPUT;
                }
                message = cause.getMessage();
            } else {
                code = INVALID_FORMAT_INPUT;
            }
            webhookService.sendPracticeLogs(payload, code, message);

        } finally {
            shutdown();
        }
    }

    public boolean isDuringContestTime() {
        Contest contest = contestDAO.findContestById(contestId);
        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime systemZoneTime = now.atZone(ZoneId.systemDefault());
        ZonedDateTime vietnamZoneTime = systemZoneTime.withZoneSameInstant(ZoneId.of("Asia/Ho_Chi_Minh"));

        LocalDateTime nowInVietnam = vietnamZoneTime.toLocalDateTime();
        System.out.println("Now: " + now);
        System.out.println("start: " + contest.getStartTime());
        System.out.println("end: " + contest.getEndTime());
        return !(nowInVietnam.isBefore(contest.getStartTime()) || nowInVietnam.isAfter(contest.getEndTime()));
    }

    public void updatePracticeExerciseStatus(int code) {
        if (code == ACCEPTED) {
            practiceUserExerciseDAO.updateContestUserExercise(practiceUserExerciseId, code == ACCEPTED);
        }
        practiceSubmissionDAO.insertPracticeSubmission(practiceUserExerciseId, LocalDateTime.now(), code == ACCEPTED, "");
        webhookService.sendPracticeLogs(payload, code, "");
        webhookService.sendRequestToUpdatePracticeScoreBoard(payload, this.userId);
    }

    public void updateContestExerciseStatus(int code) {
        if (code == ACCEPTED) {
            contestUserExerciseDAO.updateContestUserExercise(contestUserExerciseId, code == ACCEPTED);
        }
        contestSubmissionDAO.insertContestSubmission(contestUserExerciseId, LocalDateTime.now(), code == ACCEPTED, "");
        webhookService.sendContestLogs(payload, contestId, contestUserId, code, "");
        webhookService.sendRequestToUpdateContestScoreBoard(payload, this.contestId, this.contestUserId);
    }

    public void validatePracticeExercise() {
        userId = userDAO.findUserIdByStudentCodeAndIP(payload.getUsername(), payload.getIp());
//        userId = userDAO.findUserIdByStudentCode(payload.getUsername());
        if (userId == null) {
            String message = "Chưa đăng kí hoặc IP " + payload.getIp() +" không trùng khớp!";
            throw new ForbiddenException(message);
        }
        practiceUserExerciseId = practiceUserExerciseDAO.findPracticeUserExerciseByAliasCode(payload.getAlias());
        if (practiceUserExerciseId == null) {
            String message = "Mã bài tập không hợp lệ!";
            throw new WrongExerciseException(message);
        }
        exerciseId = exerciseDAO.findExerciseByAliasCode(payload.getAlias());
    }

    public boolean isValidContestExercise() {
        userId = userDAO.findUserIdByStudentCodeAndIP(payload.getUsername(), payload.getIp());
//        userId = userDAO.findUserIdByStudentCode(payload.getUsername());
        if (userId == null) {
            String message = "Chưa đăng kí hoặc IP không trùng khớp!";
            webhookService.sendContestLogs(payload, this.contestId, this.contestUserId, FORBIDDEN, message);
            shutdown();
            return false;
        }

        this.contestUserId = contestUserDAO.findContestUserByUserIdAndContestId(userId, contestId);
        if (this.contestUserId == null) {
            String message = "Không được tham gia cuộc thi này!";
            webhookService.sendContestLogs(payload, this.contestId, this.contestUserId, FORBIDDEN, message);
            shutdown();
            return false;
        }
        exerciseId = exerciseDAO.findExerciseByAliasCode(payload.getAlias());
        if (exerciseId == null) {
            String message = "Mã bài tập không hợp lệ!";
            webhookService.sendContestLogs(payload, this.contestId, this.contestUserId, FORBIDDEN, message);
            shutdown();
            return false;
        }
        this.ContestExerciseId = contestExerciseDAO.findContestExerciseByExerciseIdAndContestId(exerciseId, contestId);
        if (this.ContestExerciseId == null) {
            String message = "Mã bài tập không hợp lệ!";
            webhookService.sendContestLogs(payload, this.contestId, this.contestUserId, FORBIDDEN, message);
            shutdown();
            return false;
        }
        contestUserExerciseId = contestUserExerciseDAO.findContestUserExerciseByAliasCode(payload.getAlias());
        if (contestUserExerciseId == null) {
            String message = "Mã bài tập không hợp lệ!";
            webhookService.sendContestLogs(payload, this.contestId, this.contestUserId, REQUEST_WRONG_EXERCISE, message);
            shutdown();
            return false;
        } else {
            return true;
        }
    }

    public void validate(String requestCode) {
        if (helper.isValidateRequestCode(requestCode)) {
            String[] code = requestCode.split(";");
            String username = code[0];
            String alias = code[1];
            payload.setUsername(username);
            payload.setAlias(alias);
//            if (alias.length() == ALIAS_PRACTICE_LENGTH) {
//                this.type = PRACTICE_TYPE;
            this.initPracticeDAO();
            validatePracticeExercise();
//            } else if (alias.length() == ALIAS_CONTEST_LENGTH) {
//                this.type = CONTEST_TYPE;
//                this.initContestDAO();
//                return isValidContestExercise();
//            }
        } else {
//            throw new MalformedRequestCodeException(requestCode);
            // not throw => Not send
        }
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
