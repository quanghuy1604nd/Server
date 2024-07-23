/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author QuangHuy
 */
public class AppConstants {
    // Remote Server
//    public static final String JDBC_URL = "jdbc:postgresql://db_container:5432/ltm_demo";
//    public static final String WEBHOOK_LOG_ENDPOINT = "http://20.198.242.126:8080/api/webhook/log";
//    public static final String WEBHOOK_SCOREBOARD_ENDPOINT = "http://20.198.242.126:8080/api/webhook/scoreboard";
//    public static final String JDBC_URL = "jdbc:postgresql://db_container:5432/ltm_demo";

    
    // dev
    public static final String WEBHOOK_LOG_ENDPOINT = "http://localhost:8080/api/webhook/log";
    public static final String WEBHOOK_SCOREBOARD_ENDPOINT = "http://localhost:8080/api/webhook/scoreboard";
    public static final String JDBC_URL = "jdbc:postgresql://localhost:5432/ltm_demo";

    public static final String CLASS_FOR_NAME = "org.postgresql.Driver";
    public static final String JDBC_USERNAME = "postgres";
    public static final String JDBC_PASSWORD = "1234";


    public static final int TIME_OUT_DURATION = 5000;

    public static final int INPUT_STREAM_PORT = 1604;
    public static final int DATA_STREAM_PORT = 1605;
    public static final int CHARACTER_STREAM_PORT = 1606;
    public static final int OBJECT_STREAM_PORT = 1608;
    
    public static final int MAX_CONNECTION = Math.max(2, Runtime.getRuntime().availableProcessors() - 2);
    public static final int MAX_CONNECTION_PER_MINUTE = 10;
    public static final int WAITING_TIME = 10;

    // Exercise response status code
    public static final int ACCEPTED = 100;
    public static final int WRONG_ANSWER = 101;
    public static final int INVALID_FORMAT_INPUT = 102;
    public static final int TIME_OUT = 103;
    public static final int REQUEST_WRONG_EXERCISE = 104;
    public static final int INVALID_USER = 105;
    public static final int FORBIDDEN = 106;


}
