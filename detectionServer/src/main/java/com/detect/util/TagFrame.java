package com.detect.util;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.Frame;

/**
 * Created by wxy on 2017/12/10.
 */
public class TagFrame {
    public long idx;
    public Frame origframe;
    public Frame curframe;
    public TagFrame(Frame origframe,long idx){
        this.origframe = origframe;
        this.idx = idx;
    }
}
