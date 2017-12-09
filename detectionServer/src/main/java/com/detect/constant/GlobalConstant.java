package com.detect.constant;

/**
 * Created by Administrator on 2017/12/08.
 */
public class GlobalConstant {
    /** error code **/
    public static int ERR_CTL_SUCCESS = 0;
    public static int ERR_CTL_GETDATA = 1;
    public static int ERR_CTL_INSERTDATA = 2;
    public static int ERR_CTL_UPDATEDATA = 3;
    public static int ERR_CTL_DELETEDATA = 4;
    public static int ERR_CTL_PARAMISTAKE = 5;
    public static int ERR_CTL_NODATA = 6;
    public static int ERR_CTL_TIMEOUT = 7;
    public static int ERR_SRV_DATAEXIST = 8;
    public static int ERR_SRV_NODATA = 9;


    /** response code **/
    public static String RESP_CODE_SUCCESS = String.valueOf(ERR_CTL_SUCCESS);
    public static String RESP_CODE_FAIL_GETDATA = String.valueOf(ERR_CTL_GETDATA);
    public static String RESP_CODE_FAIL_INSERTDATA = String.valueOf(ERR_CTL_INSERTDATA);
    public static String RESP_CODE_FAIL_UPDATEDATA = String.valueOf(ERR_CTL_UPDATEDATA);
    public static String RESP_CODE_FAIL_DELETEDATA = String.valueOf(ERR_CTL_DELETEDATA);
    public static String RESP_CODE_FAIL_PARAMISTAKE = String.valueOf(ERR_CTL_PARAMISTAKE);
    public static String RESP_CODE_FAIL_CTL_NODATA = String.valueOf(ERR_CTL_NODATA);
    public static String RESP_CODE_FAIL_CTL_TIMEOUT = String.valueOf(ERR_CTL_TIMEOUT);
    public static String RESP_CODE_FAIL_SRV_DATAEXIST = String.valueOf(ERR_SRV_DATAEXIST);
    public static String RESP_CODE_FAIL_SRV_NODATA = String.valueOf(ERR_SRV_NODATA);

    public static boolean RESP_SUCCESS = true;
    public static boolean RESP_FAIL = false;

    /** response message **/
    public static String RESP_MSG_NODATA = "no data";
    public static String RESP_MSG_GETDATAFAIL = "get data from db fail";
    public static String RESP_MSG_INSERTDATAFAIL = "insert data to db fail";
    public static String RESP_MSG_INSERTDATAFAIL_EXIST = "insert data to db that exist";
    public static String RESP_MSG_UPDATEDATAFAIL = "update data to db fail";
    public static String RESP_MSG_DELETEDATAFAIL = "delete data from db fail";
    public static String RESP_MSG_PARAMISTAKE = "parameter mistake";
}
