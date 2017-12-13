package com.detect.util;

import com.detect.driver.MeVcl;
import org.bytedeco.javacpp.opencv_core;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wxy on 2017/12/9.
 */
public class Grabber {
    @Autowired
    MeVcl meVcl;
    public void start(){

    }
    public void stop(){

    }
    public TagFrame grab(){
        return  new TagFrame(new opencv_core.Mat(),1);
    }
}
