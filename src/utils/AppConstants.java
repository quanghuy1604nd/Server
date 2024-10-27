/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author QuangHuy
 */
public class AppConstants {
    
    public static class RandomConstants {
        public static final String ALL_LOWER_LETTERS = "abcdefghijklmnopqrstuvwxyz";
        public static final String ALL_LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWYZ";
    }

    public static class WebhookConstants {

        public static final String WEBHOOK_TOKEN = "Z3JhZHVhdGlvbnRoZXNpcw==";
        public static final String WEBHOOK_EXAM_LOG_ENDPOINT = System.getenv().getOrDefault("WEBHOOK_EXAM_LOG_ENDPOINT", "http://localhost:8080/v1/api/webhook/exams/logs");
        public static final String WEBHOOK_LEADER_BOARD_ENDPOINT = System.getenv().getOrDefault("WEBHOOK_LEADER_BOARD_ENDPOINT", "http://localhost:8080/v1/api/webhook/exams/leaderboard");

    }

    // Remote Server
    public static class DaoConstants {

        public static final String JDBC_URL = System.getenv().getOrDefault("JDBC_URL", "jdbc:postgresql://localhost:5432/graduation_thesis2");
        public static final String JDBC_USERNAME = System.getenv().getOrDefault("JDBC_USERNAME", "postgres");
        public static final String JDBC_PASSWORD = System.getenv().getOrDefault("JDBC_PASSWORD", "1");
        public static final String CLASS_FOR_NAME = "org.postgresql.Driver";
    }

    public static class ProcessCodeConstants {

        public static final int CONNECT_SUCCESS = 99;
        public static final int ACCEPTED = 100;
        public static final int WRONG_ANSWER = 101;
        public static final int INVALID_INPUT_FORMAT = 102;
        public static final int TIME_OUT = 103;
        public static final int INVALID_QUESTION = 104;
        public static final int MALFORMED_REQUEST_CODE = 105;
        public static final int FORBIDDEN = 106;
        public static final int OTHER = 107;
    }

    public static class PortConfiguration {

        public static final int INPUT_STREAM_PORT = 1604;
        public static final int DATA_STREAM_PORT = 1605;
        public static final int CHARACTER_STREAM_PORT = 1606;
        public static final int OBJECT_STREAM_PORT = 1607;
    }

    public static class AppConfiguration {

        public static final int MAX_CONNECTION = Math.max(2, Runtime.getRuntime().availableProcessors() - 2);
        public static final int MAX_CONNECTION_PER_MINUTE = 10;
        public static final int WAITING_TIME = 60;
    }

    public static final int TIME_OUT_DURATION = 5000;

}
