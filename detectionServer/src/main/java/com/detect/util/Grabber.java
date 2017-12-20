package com.detect.util;

import com.detect.driver.MeVcl;
import org.bytedeco.javacpp.opencv_core;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wxy on 2017/12/9.
 */
public class Grabber {
    public static int RETURN_OK = 0;
    public static int RETURN_FAIL = 1;
    @Autowired
    MeVcl meVcl;
    MeVcl.Fg fg;
    public long idx = 0;
    public static double ACQUIRE_NUM_INFINITE=0;
    public static double FRAME_BUFFER_LENGTH=0;
    public static int STATUA_INITED=0;
    public static int STATUA_STARTED=1;
    public static int STATUA_GRABBING=2;
    private int status = STATUA_INITED;

    public int start(){
        if(meVcl.start() != 0){
            return RETURN_FAIL;
        }
        status = STATUA_STARTED;
        return RETURN_OK;
    }
    public int stop(){

        if(meVcl.stop() != 0){
            return RETURN_FAIL;
        }
        status = STATUA_INITED;
        return RETURN_OK;
    }
    public TagFrame grab(){
        status = STATUA_GRABBING;
        opencv_core.Mat mat = new opencv_core.Mat();
        byte[] raw_data = meVcl.grab();
        //mat.put(0, 0, raw_data);
        return  new TagFrame(mat,idx++);
    }
}
