/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contest;

import dao.ExerciseContestDAO;
import dao.SubmissionDAO;
import dao.UserContestDAO;
import dao.UserDAO;
import dao.UserExerciseContestDAO;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import service.IWebhookService;
import service.WebhookServiceImpl;

/**
 *
 * @author QuangHuy
 */
public class GeneralExercise {

    protected final Socket socket;
    protected final ExerciseContestDAO exerciseContestDAO;
    protected final SubmissionDAO submissionDAO;
    protected final UserDAO userDAO;
    protected final UserContestDAO userContestDAO;
    protected final UserExerciseContestDAO userExerciseContestDAO;
    protected final IWebhookService webhookService;
    protected Long userExerciseContestId;
    protected Long userId;
    protected Long exerciseId;

    public GeneralExercise(Socket socket) {
        this.socket = socket;
        this.exerciseContestDAO = new ExerciseContestDAO();
        this.submissionDAO = new SubmissionDAO();
        this.userDAO = new UserDAO();
        this.userContestDAO = new UserContestDAO();
        this.userExerciseContestDAO = new UserExerciseContestDAO();
        this.webhookService = new WebhookServiceImpl();
    }

    public boolean isValidateRequestCode(String requestCode) {
        String regexRequest = "^[Bb]\\d{2}[A-Za-z]{4}\\d{3};\\d+$";
        Pattern pattern = Pattern.compile(regexRequest);
        Matcher matcher = pattern.matcher(requestCode);
        return matcher.matches();
    }

    public void preprocess() {

    }

    public void process(String studentCode, String ip, String questionCode) {
        // TODO: Override to handler
    }

    public void validate(String requestCode) {
        String ip = ((InetSocketAddress) socket.getRemoteSocketAddress()).getAddress().getHostAddress();
        if (isValidateRequestCode(requestCode)) {
            String[] code = requestCode.split(";");

            Long contestId = 1L;

            String studentCode = code[0];
            String qCode = code[1];
            userId = userDAO.findUserIdByStudentCodeAndIP(studentCode, ip);
            Long userContestId = userContestDAO.findUserContestIdByUserIdAndContestId(studentCode, contestId);
            Long exerciseContestId = exerciseContestDAO.findUserContestIdByUserIdAndContestId(qCode, contestId);
            if(userId == null) {
                webhookService.sendWebhookLogs(String.format("Mã sinh viên %s chưa đăng kí! hoặc IP không trùng khớp", studentCode));
                shutdown();
                return;
            }
            if (userContestId == null) {
                webhookService.sendWebhookLogs(String.format("Mã sinh viên %s không được tham gia cuộc thi này!", studentCode));
                shutdown();
            }
            if (exerciseContestId == null) {
                webhookService.sendWebhookLogs(String.format("Mã sinh viên %s: mã bài tập %s không đúng!", studentCode, qCode));
                shutdown();
                return;
            }
            this.userExerciseContestId = userExerciseContestDAO.findUserExerciseContestByUserContestIdAndExerciseContestId(userContestId, exerciseContestId);
            if (userExerciseContestId == null) {
                webhookService.sendWebhookLogs(String.format("Mã sinh viên %s không được giao bài tập %s!", studentCode, qCode));
                shutdown();
                return;
            } else {
                process(studentCode, ip, qCode);
            }
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
