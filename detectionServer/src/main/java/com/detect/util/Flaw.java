package com.detect.util;

import org.bytedeco.javacpp.opencv_core;

/**
 * Created by wxy on 2017/12/10.
 */
public class Flaw {
    private int type;
    private String procid;
    private String time;
    public double idx;
    public opencv_core.Mat origframe;
    public opencv_core.Mat curframe;
    private int posx;
    private int posy;
    private int area;
}
