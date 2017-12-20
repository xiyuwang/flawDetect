package com.detect.util;

import org.bytedeco.javacpp.opencv_core;

/**
 * Created by wxy on 2017/12/10.
 */
public class TagFrame {
    public long idx;
    public opencv_core.Mat origframe;
    public opencv_core.Mat curframe;
    public TagFrame(opencv_core.Mat origframe,long idx){
        this.origframe = origframe;
        this.idx = idx;
    }
}
