/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package handler;

/**
 *
 * @author QuangHuy
 */
import contest.E1;
import dao.AliasDAO;
import dao.ContestDAO;
import dao.ExerciseContestDAO;
import dao.ExerciseDAO;
import dao.SubmissionDAO;
import dao.UserContestDAO;
import dao.UserDAO;
import dao.UserExerciseContestDAO;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.time.LocalDateTime;
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
    protected Long userExerciseContestId;
    protected Long userId;
    protected Long exerciseId;

    public ClientHandler(Socket socket) {
        this.socket = socket;
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
            System.out.println(socket.getLocalPort());
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
        return !(now.isBefore(contest.getStartTime()) || now.isAfter(contest.getEndTime()));
    }

    public boolean isValidateRequestCode(String requestCode) {
        String regexRequest = "^[Bb]\\d{2}[A-Za-z]{4}\\d{3};\\d+$";
        Pattern pattern = Pattern.compile(regexRequest);
        Matcher matcher = pattern.matcher(requestCode);
        return matcher.matches();
    }

    public void updateExerciseContestStatus(int code) {
        String message = code == ACCEPTED ? "Làm đúng, good job bro" : "Chưa đúng, làm lại";
        webhookService.sendWebhookLogs(message);
        if (code == ACCEPTED) {
            userExerciseContestDAO.updateUserExerciseContest(userExerciseContestId, code == ACCEPTED);
        }
        submissionDAO.insertSubmission(userExerciseContestId, LocalDateTime.now(), code == ACCEPTED, "");
        webhookService.sendWebhookUpdateScoreBoard(userId);
    }

    public boolean isValid(String requestCode) {
        if (isDuringContestTime()) {
            String ip = ((InetSocketAddress) socket.getRemoteSocketAddress()).getAddress().getHostAddress();
            if (isValidateRequestCode(requestCode)) {
                String[] code = requestCode.split(";");
                String studentCode = code[0];
                String qCode = code[1];
                exerciseId = exerciseDAO.findExerciseIdByAliasCode(qCode);
                userId = userDAO.findUserIdByStudentCodeAndIP(studentCode, ip);
                if (userId == null) {
                    webhookService.sendWebhookLogs(String.format("Mã sinh viên %s chưa đăng kí hoặc IP không trùng khớp", studentCode));
                    shutdown();
                    return false;
                }
                Long userContestId = userContestDAO.findUserContestIdByUsernameAndContestId(studentCode, contestId);
                if (userContestId == null) {
                    webhookService.sendWebhookLogs(String.format("Mã sinh viên %s không được tham gia cuộc thi này!", studentCode));
                    shutdown();
                    return false;

                }
                Long exerciseContestId = exerciseContestDAO.findExerciseContestIdByExerciseIdAndContestId(exerciseId, contestId);
                if (exerciseContestId == null) {
                    webhookService.sendWebhookLogs(String.format("Mã sinh viên %s: mã bài tập %s không đúng!", studentCode, qCode));
                    shutdown();
                    return false;
                }
                Long aliasId = aliasDAO.findAliasIdByCode(qCode);
                userExerciseContestId = userExerciseContestDAO.findUserExerciseContestByUserContestIdAndExerciseContestId(userContestId, exerciseContestId, aliasId);
                if (userExerciseContestId == null) {
                    webhookService.sendWebhookLogs(String.format("Mã sinh viên %s không được giao bài tập %s!", studentCode, qCode));
                    shutdown();
                    return false;
                } else {
                    return true;
                }
            }
        }
        shutdown();
        return false;
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
