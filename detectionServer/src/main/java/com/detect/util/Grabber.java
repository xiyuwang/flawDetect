package com.detect.util;

import org.bytedeco.javacpp.opencv_core;

/**
 * Created by wxy on 2017/12/9.
 */
public class Grabber {
    public void start(){

    }
    public void stop(){

    }
    public TagFrame grab(){
        return  new TagFrame(new opencv_core.Mat(),1);
    }
}
