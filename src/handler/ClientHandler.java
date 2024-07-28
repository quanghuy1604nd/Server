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
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Contest;
import payload.Payload;
import service.IWebhookService;
import service.WebhookServiceImpl;
import static util.AppConstants.*;

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

    protected int type;
    protected Payload payload;

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
        try {
            socket.setSoTimeout(TIME_OUT_DURATION);
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
        ZonedDateTime systemZoneTime = now.atZone(ZoneId.systemDefault());
        ZonedDateTime vietnamZoneTime = systemZoneTime.withZoneSameInstant(ZoneId.of("Asia/Ho_Chi_Minh"));

        LocalDateTime nowInVietnam = vietnamZoneTime.toLocalDateTime();
        System.out.println("Now: " + now);
        System.out.println("start: " + contest.getStartTime());
        System.out.println("end: " + contest.getEndTime());
        return !(nowInVietnam.isBefore(contest.getStartTime()) || nowInVietnam.isAfter(contest.getEndTime()));
    }

    public boolean isValidateRequestCode(String requestCode) {
        String regexRequest = "^[Bb]\\d{2}[A-Za-z]{4}\\d{3};[a-zA-Z0-9]{7,8}$";
        Pattern pattern = Pattern.compile(regexRequest);
        Matcher matcher = pattern.matcher(requestCode);
        return matcher.matches();
    }

    public String getResultMessage(int code) {
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
        return message;
    }

    public void updatePracticeExerciseStatus(int code) {
        String message = getResultMessage(code);
        webhookService.sendPracticeLogs(payload, code, message);
        if (code == ACCEPTED) {
            practiceUserExerciseDAO.updateContestUserExercise(practiceUserExerciseId, code == ACCEPTED);
        }
        practiceSubmissionDAO.insertPracticeSubmission(practiceUserExerciseId, LocalDateTime.now(), code == ACCEPTED, "");
        webhookService.sendRequestToUpdatePracticeScoreBoard(payload, this.userId);
    }

    public void updateContestExerciseStatus(int code) {
        String message = getResultMessage(code);
        webhookService.sendContestLogs(payload, this.contestId, this.contestUserId, code, message);
        if (code == ACCEPTED) {
            contestUserExerciseDAO.updateContestUserExercise(contestUserExerciseId, code == ACCEPTED);
        }
        contestSubmissionDAO.insertContestSubmission(contestUserExerciseId, LocalDateTime.now(), code == ACCEPTED, "");
        webhookService.sendRequestToUpdateContestScoreBoard(payload, this.contestId, this.contestUserId);
    }

    public boolean isValidPracticeExercise() {
        userId = userDAO.findUserIdByStudentCode(payload.getUsername());
        if (userId == null) {
            String message = "Chưa đăng kí hoặc IP không trùng khớp!";
            webhookService.sendPracticeLogs(payload, FORBIDDEN, message);
            shutdown();
            return false;
        }
        practiceUserExerciseId = practiceUserExerciseDAO.findPracticeUserExerciseByAliasCode(payload.getAlias());
        if (practiceUserExerciseId == null) {
            String message = "Mã bài tập không hợp lệ!";
            webhookService.sendPracticeLogs(payload, REQUEST_WRONG_EXERCISE, message);
            shutdown();
            return false;
        }
        exerciseId = exerciseDAO.findExerciseByAliasCode(payload.getAlias());
        return true;
    }

    public boolean isValidContestExercise() {
//        userId = userDAO.findUserIdByStudentCodeAndIP(payload.getUsername(), payload.getIp());
        userId = userDAO.findUserIdByStudentCode(payload.getUsername());
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

    public boolean isValid(String requestCode) {
        if (isValidateRequestCode(requestCode)) {
            String[] code = requestCode.split(";");
            String username = code[0];
            String alias = code[1];

            payload.setUsername(username);
            payload.setAlias(alias);
            if (alias.length() == ALIAS_PRACTICE_LENGTH) {
                this.type = PRACTICE_TYPE;
                this.initPracticeDAO();
                return isValidPracticeExercise();
            } else if (alias.length() == ALIAS_CONTEST_LENGTH) {
                this.type = CONTEST_TYPE;
//                this.initContestDAO();
//                return isValidContestExercise();
            }
        }
//        System.out.println("Contest is over!");
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

                if (this.type == CONTEST_TYPE) {
                    this.updateContestExerciseStatus(result);
                } else {
                    this.updatePracticeExerciseStatus(result);
                }
            } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            
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
