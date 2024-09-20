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
import exception.InvalidRequestException;
import exception.StepErrorException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.net.Socket;
import model.Alias;
import model.ExamUserDetail;
import model.Question;
import model.User;
import payload.LogPayload;
import payload.RankPayload;
import service.IWebhookService;
import service.WebhookServiceImpl;
import static utils.AppConstants.*;
import static utils.AppConstants.PortConfiguration.*;
import static utils.AppConstants.ProcessCodeConstants.ACCEPTED;
import static utils.AppConstants.ProcessCodeConstants.OTHER;
import static utils.AppConstants.ProcessCodeConstants.WRONG_ANSWER;
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

    protected LogPayload logPayload;
    protected RankPayload rankPayload;
    protected Long test;

    protected User user;
    protected Alias alias;
    protected Question question;
    protected ExamUserDetail examUserDetail;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        this.logPayload = new LogPayload();
        this.logPayload.setClientInfo(((InetSocketAddress) socket.getRemoteSocketAddress()).getAddress().getHostAddress());
    }

    public void initDAO() {
        this.examDetailDAO = new ExamDetailDAO();
        this.submissionDAO = new SubmissionDAO();
        this.examUserDAO = new ExamUserDAO();
        this.examUserDetailDAO = new ExamUserDetailDAO();
        this.userDAO = new UserDAO();
        this.questionDAO = new QuestionDAO();
        this.aliasDAO = new AliasDAO();
        this.webhookService = new WebhookServiceImpl();
    }

    @Override
    public void run() {
        String message;
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
                    throw new InvalidRequestException("");
                }
            }
            initDAO();
            Pair<String, String> clientInfo = judge.extractClientInfo();
            this.logPayload.setUsername(clientInfo.getKey());
            this.logPayload.setAliasName(clientInfo.getValue());
            getUser();
            getAlias();
            getExamUserDetail();
            getQuestion();
            boolean result = judge.process(question.getCode());
            this.logPayload.setProcessCode(result ? ACCEPTED : WRONG_ANSWER);
            this.logPayload.setProcessLog(result ? "ACCCEPTED" : "WRONG ANSWER");
            this.rankPayload = new RankPayload(
                    this.examUserDetail.getExamUserId(),
                    this.examUserDetail.getId(),
                    result
            );
            webhookService.sendUpdateLeaderBoard(rankPayload);

        } catch (Exception ex) {
            if (ex instanceof InvocationTargetException) {
                message = ex.getCause().getMessage();
                this.logPayload.setProcessCode(((StepErrorException) (ex.getCause())).getProcessCode());
            } else {
                message = ex.getMessage();
                this.logPayload.setProcessCode(OTHER);
            }
            this.logPayload.setProcessLog(message);
        } finally {
            webhookService.sendExamLogs(this.logPayload);
            shutdown();
        }
    }

    public void getUser() throws InvalidRequestException {
        this.user = userDAO.findByUsernameAndIpAddress(this.logPayload.getUsername(), this.logPayload.getClientInfo());
        if (this.user == null) {
            throw new InvalidRequestException(String.format("User info [username: %s, ipAddress: %s] is not valid", this.logPayload.getUsername(), this.logPayload.getClientInfo()));
        }
        this.logPayload.setUserId(this.user.getId());
    }

    public void getAlias() throws InvalidRequestException {
        this.alias = aliasDAO.findByNameAndActive(this.logPayload.getAliasName(), true);
        if (this.alias == null) {
            throw new InvalidRequestException(String.format("Question [%s] is not valid", this.logPayload.getAliasName()));
        }
    }

    public void getExamUserDetail() throws InvalidRequestException {
        this.examUserDetail = examUserDetailDAO.findById(this.alias.getExamUserDetailId());
        if (this.examUserDetail == null) {
            throw new InvalidRequestException(String.format("Question [%s] is not valid", this.logPayload.getAliasName()));
        }
        this.logPayload.setExamUserDetailId(this.examUserDetail.getId());
        this.logPayload.setExamUserId(this.examUserDetail.getExamUserId());

    }

    public void getQuestion() throws InvalidRequestException {
        this.question = questionDAO.findByExamDetailId(this.examUserDetail.getExamDetailId());
        if (this.question == null) {
            throw new InvalidRequestException(String.format("Question [%s] is not valid", this.logPayload.getAliasName()));
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
