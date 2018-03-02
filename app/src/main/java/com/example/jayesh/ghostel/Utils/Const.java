package com.example.jayesh.ghostel.Utils;

/**
 * Created by jayesh on 13/2/18.
 */

public class Const
{
    // connection timeout is set to 20 seconds
    public static int TIMEOUT_CONNECTION = 90000;

    // SOCKET TIMEOUT IS SET TO 30 SECONDS
    public static int TIMEOUT_SOCKET = 90000;


    // Api Response
    public static enum API_RESULT
    {
        SUCCESS, FAIL
    }

    	/* Default Message */

    public static final String NETWORK_ERROR = "networkError";
    public static final String LOGIN_REQUIRED = "loginRequired";
    public static final String NO_AUTHORIZATION = "noAuthorization";
    public static final String API_ERROR = "apiError";
    public static final String SERVER_ERROR = "serverError";
    public static String API_ALERT = "MyRunningRecord";


    public static final String API_URL = "http://192.168.43.31/ghostel/";
    public static final String API_HOSTELLIST = "hostellist.php";
    public static final String API_BLOCKLIST = "blocklist.php";
    public static final String API_ADDNEWBLOCK = "inst_block.php";
    public static final String API_ADDNEWHOSTEL = "inst_hostel.php";
    public static final String API_ADDNEWRECTOR = "inst_rector.php";
    public static final String API_ADDNEWCONTRACTOR = "inst_contractor.php";
    public static final String API_ADDNEWSTUDENT = "inst_student.php";
}
