package com.example.jayesh.ghostel.Utils;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

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
    //public static final String API_URL = "https://ghostel.000webhostapp.com/";
    public static final String API_HOSTELLIST = "hostellist.php";
    public static final String API_BLOCKLIST = "blocklist.php";
    public static final String API_ROOMLIST = "roomlist.php";
    public static final String API_GETBLOCKS = "getblocks.php";
    public static final String API_GETROOMS = "getrooms.php";
    public static final String API_ADDNEWBLOCK = "inst_block.php";
    public static final String API_ADDNEWROOM = "inst_room.php";
    public static final String API_ADDNEWHOSTEL = "inst_hostel.php";
    public static final String API_ADDNEWRECTOR = "inst_rector.php";
    public static final String API_ADDNEWCONTRACTOR = "inst_contractor.php";
    public static final String API_ADDNEWSTUDENT = "inst_student.php";
    public static final String API_VALIDATEUSER = "validateuser.php";
    public static final String API_RECTORLIST = "rectorlist.php";
    public static final String API_CONTRACTORLIST = "contractorlist.php";
    public static final String API_STUDENTLIST = "studentlist.php";
    public static final String API_COMPLAINLIST = "complainlist.php";
    public static final String API_SAVEQR = "inst_code.php";
    public static final String API_VALIDATEQR = "validateCode.php";
    public static final String API_VIEWHOSTEL = "viewHostel.php";
    public static final String API_VIEWBLOCK = "viewBlock.php";
    public static final String API_VIEWROOM = "viewRoom.php";
    public static final String API_VIEWSTUDENT = "viewStudent.php";
    public static final String API_VIEWRECTOR = "viewRector.php";
    public static final String API_VIEWCONTRACTOR = "viewContractor.php";
    public static final String API_EDITSTUDENT = "editStudent.php";
    public static final String API_EDITRECTOR = "editRector.php";
    public static final String API_EDITCONTRACTOR = "editContractor.php";
    public static final String API_EDITHOSTEL = "editHostel.php";
    public static final String API_DELHOSTEL = "deleteHostel.php";
    public static final String API_DELBLOCK = "deleteBlock.php";
    public static final String API_EDITBLOCK = "editBlock.php";
    public static final String API_DELSTUDENT = "deleteStudent.php";
    public static final String API_DELRECTOR = "deleteRector.php";
    public static final String API_DELCONTRACTOR = "deleteContractor.php";
    public static final String API_DELROOM = "deleteRoom.php";
    public static final String API_REGCOMPLAIN = "addComplain.php";
    public static final String API_CHANGEPASSWORD = "changePassword.php";
    public static final String API_CHANGEPROFILE = "changeProfile.php";
    public static final String API_POSTCOMMENT = "postComment.php";
    public static final String API_COMMENTLIST = "commentList.php";
}
