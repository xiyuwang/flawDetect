package com.detect.driver;

/**
 * Created by Administrator on 2017/12/12.
 */
public class MeVcl {
    public class Fg {
    }
    public class Dmamem{

    }
    public native static Fg init(String fileName, int boardIndex);
    public native static Fg initEx(String fileName, int boardIndex, int isSlave);
    public native static Fg initConfig(String configName, int boardIndex);
    public native static int saveConfig (Fg fg, String filename);
    public native static int setParameter (Fg fg, int parameter, Object value, int dmaIndex);
    public native static Dmamem allocMem(Fg fg, int Size, int bufCnt, int dmaIndex);
    public native static Dmamem allocMemEx(Fg fg, int Size, int BufCnt);
    public native static int freeMem (Fg fg, int dmaIndex);
    public native static int acquire(Fg fg, int dmaIndex, double picCount);
    public native static int freeGrabber(Fg fg);

    public int start(){
        int ret = 0;
        return ret;
    }

    public int stop(){
        int ret = 0;
        return ret;
    }

    public byte[] grab(){
        return new byte[10];
    }
}
