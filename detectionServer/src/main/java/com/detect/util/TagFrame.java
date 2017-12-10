package com.detect.util;

import org.bytedeco.javacpp.opencv_core;

/**
 * Created by wxy on 2017/12/10.
 */
public class TagFrame {
    public double idx;
    public opencv_core.Mat origframe;
    public opencv_core.Mat curframe;
    public TagFrame(opencv_core.Mat origframe,double idx){
        this.origframe = origframe;
        this.idx = idx;
    }
}
