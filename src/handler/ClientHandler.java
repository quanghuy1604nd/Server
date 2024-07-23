/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package handler;

/**
 *
 * @author QuangHuy
 */
import contest.E2;
import dao.AliasDAO;
import dao.ContestDAO;
import dao.ExerciseContestDAO;
import dao.ExerciseDAO;
import dao.SubmissionDAO;
import dao.UserContestDAO;
import dao.UserDAO;
import dao.UserExerciseContestDAO;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Contest;
import service.IWebhookService;
import service.WebhookServiceImpl;
import static util.AppConstants.*;

public class ClientHandler implements Runnable {

    protected final Socket socket;
    protected final ExerciseContestDAO exerciseContestDAO;
    protected final SubmissionDAO submissionDAO;
    protected final UserDAO userDAO;
    protected final ExerciseDAO exerciseDAO;
    protected final ContestDAO contestDAO;
    protected final UserContestDAO userContestDAO;
    protected final UserExerciseContestDAO userExerciseContestDAO;
    protected final AliasDAO aliasDAO;
    protected final IWebhookService webhookService;
    protected final Long contestId;
    protected final String ip;
    protected String alias;
    protected String username;
    protected Long userExerciseContestId;
    protected Long userId;
    protected Long exerciseId;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        this.ip = ((InetSocketAddress) socket.getRemoteSocketAddress()).getAddress().getHostAddress();
        this.exerciseContestDAO = new ExerciseContestDAO();
        this.submissionDAO = new SubmissionDAO();
        this.userDAO = new UserDAO();
        this.exerciseDAO = new ExerciseDAO();
        this.contestDAO = new ContestDAO();
        this.userContestDAO = new UserContestDAO();
        this.aliasDAO = new AliasDAO();
        this.userExerciseContestDAO = new UserExerciseContestDAO();
        this.webhookService = new WebhookServiceImpl();
        this.contestId = 1L;
    }

    @Override
    public void run() {
        try {
            socket.setSoTimeout(TIME_OUT_DURATION);
            System.out.println(this.ip);
            switch (socket.getLocalPort()) {
                case INPUT_STREAM_PORT -> {
                    InputStreamHandler inputStreamHandler = new InputStreamHandler(this.socket);
                    inputStreamHandler.process();

                }
                case DATA_STREAM_PORT -> {
                    DataStreamHandler dataStreamHandler = new DataStreamHandler(this.socket);
                    dataStreamHandler.process();
                }
                case CHARACTER_STREAM_PORT -> {
                    CharacterStreamHandler characterStreamHandler = new CharacterStreamHandler(this.socket);
                    characterStreamHandler.process();
                }
                case OBJECT_STREAM_PORT -> {
                }
                default ->
                    System.out.println("Unknown stream type");
            }
        } catch (IOException ex) {
        } finally {
            shutdown();
        }
    }

    public boolean isDuringContestTime() {
        Contest contest = contestDAO.findContestById(contestId);
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Now: " + now);
        System.out.println("start: " + contest.getStartTime());
        System.out.println("end: " + contest.getEndTime());
        return !(now.isBefore(contest.getStartTime()) || now.isAfter(contest.getEndTime()));
    }

    public boolean isValidateRequestCode(String requestCode) {
        String regexRequest = "^[Bb]\\d{2}[A-Za-z]{4}\\d{3};\\d+$";
        Pattern pattern = Pattern.compile(regexRequest);
        Matcher matcher = pattern.matcher(requestCode);
        return matcher.matches();
    }

    public void updateExerciseContestStatus(int code) {
        String message;
        switch (code) {
            case TIME_OUT ->
                message = "Quá thời gian!";
            case INVALID_FORMAT_INPUT ->
                message = "Gửi sai định dạng";
            case WRONG_ANSWER ->
                message = "Sai kết quả";
            case ACCEPTED ->
                message = "Hoàn thành";
            default ->
                message = "Lỗi server ???";
        }

        webhookService.sendWebhookLogs(this.ip, this.username, this.alias, code, message);
        if (code == ACCEPTED) {
            userExerciseContestDAO.updateUserExerciseContest(userExerciseContestId, code == ACCEPTED);
        }
        submissionDAO.insertSubmission(userExerciseContestId, LocalDateTime.now(), code == ACCEPTED, "");
        webhookService.sendWebhookUpdateScoreBoard(userId);
    }

    public boolean isValid(String requestCode) {
        if (isDuringContestTime()) {
            if (isValidateRequestCode(requestCode)) {
                String[] code = requestCode.split(";");
                username = code[0];
                alias = code[1];
//                userId = userDAO.findUserIdByStudentCodeAndIP(username, ip);
                userId = userDAO.findUserIdByStudentCode(username);
                System.out.println(userId);
                if (userId == null) {
                    String message = "Chưa đăng kí hoặc IP không trùng khớp!";
                    webhookService.sendWebhookLogs(ip, username, "", FORBIDDEN, message);
                    shutdown();
                    return false;
                }
                exerciseId = exerciseDAO.findExerciseIdByAliasCode(alias);
                if (exerciseId == null) {
                    String message = "Yêu cầu sai bài tập!";
                    webhookService.sendWebhookLogs(ip, username, "", FORBIDDEN, message);
                    shutdown();
                    return false;
                }
                Long userContestId = userContestDAO.findUserContestIdByUsernameAndContestId(username, contestId);
                if (userContestId == null) {
                    String message = "Không được tham gia cuộc thi này!";
                    webhookService.sendWebhookLogs(ip, username, "", FORBIDDEN, message);
                    shutdown();
                    return false;

                }
                Long exerciseContestId = exerciseContestDAO.findExerciseContestIdByExerciseIdAndContestId(exerciseId, contestId);
                if (exerciseContestId == null) {
                    String message = "Mã bài tập không hợp lệ!";
                    webhookService.sendWebhookLogs(ip, username, "", FORBIDDEN, message);
                    shutdown();
                    return false;
                }
                Long aliasId = aliasDAO.findAliasIdByCode(alias);
                userExerciseContestId = userExerciseContestDAO.findUserExerciseContestByUserContestIdAndExerciseContestId(userContestId, exerciseContestId, aliasId);
                if (userExerciseContestId == null) {
                    String message = "Bài tập không được chỉ định!";
                    webhookService.sendWebhookLogs(ip, username, alias, REQUEST_WRONG_EXERCISE, message);
                    shutdown();
                    return false;
                } else {
                    return true;
                }
            }
        }
        System.out.println("Contest is over!");
        shutdown();
        return false;
    }

    public void judge(Class inputClass, Class outputClass, Object input, Object output, String requestCode) {
        if (isValid(requestCode)) {
            try {
                Class clazz = Class.forName("contest.E" + this.exerciseId);
                Constructor<?> constructor = clazz.getConstructor(inputClass, outputClass);
                Object instance = constructor.newInstance(input, output);
                Method process = clazz.getMethod("process");
                int result = (int) process.invoke(instance);
                this.updateExerciseContestStatus(result);
            } catch (NoSuchMethodException | ClassNotFoundException | IllegalAccessException | InvocationTargetException | InstantiationException ex) {
                // TODO handle
            }
        }
        shutdown();
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
