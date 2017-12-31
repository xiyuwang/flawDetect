package com.detect.util;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.PointerPointer;

import com.detect.driver.MeVcl;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.bytedeco.javacpp.BytePointer;

import java.awt.image.BufferedImage;

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
        Frame frame = new Frame();
        //Java2DFrameConverter converter = new Java2DFrameConverter();
        //BufferedImage bi = converter.getBufferedImage(f);

        byte[] raw_data = meVcl.grab();
        BytePointer p = new BytePointer(raw_data);
        ByteBuffer b   = p.asBuffer();
        frame.samples[0] = b;
        //mat.put(0, 0, raw_data);
        return  new TagFrame(frame,idx++);
    }
}
