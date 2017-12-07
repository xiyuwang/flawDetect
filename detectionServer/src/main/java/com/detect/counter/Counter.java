package com.detect.counter;

/**
 * Created by Administrator on 2017/12/07.
 */
public class Counter {
    public static Integer cnt_queryCameraList = 0;
    public static Integer cnt_queryCameraListSucc = 0;
    public static void incQueryCameraList() {
        cnt_queryCameraList++;
    }
    public static void incQueryCameraSucc() {
        cnt_queryCameraListSucc++;
    }
}
