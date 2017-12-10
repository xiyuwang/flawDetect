package com.detect.util;

import java.lang.Thread;
import java.util.LinkedList;
import java.util.Queue;

import org.bytedeco.javacpp.opencv_core;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wxy on 2017/12/9.
 */
public class Processor extends Thread{
    private static final Logger log = LoggerFactory.getLogger(Processor.class);
    private volatile boolean exit = false;
    private boolean ignoreOutput = false;
    private String procid;
    private String filename;
    private int type;
    private volatile Queue<TagFrame> inputQue;
    private volatile Queue<TagFrame> outputQue = new LinkedList<TagFrame>();;
    public void setInputQue(Queue<TagFrame> inputQue){
        this.inputQue = inputQue;
    }

    public void setExit(boolean exit){
        this.exit = exit;
    }
    public boolean getExit(){
        return exit;
    }
    public void setIgnoreOutput(boolean ignoreOutput){
        this.ignoreOutput = ignoreOutput;
    }
    public boolean getIgnoreOutput(){
        return ignoreOutput;
    }
    public void setProcid(String procid){
        this.procid = procid;
    }
    public String getProcid(){
        return procid;
    }
    public void setFilename(String filename){
        this.filename = filename;
    }
    public String getFilename(){
        return filename;
    }
    public void setType(int type){
        this.type = type;
    }
    public int getType(){
        return type;
    }
    public void setOutputQue(Queue<TagFrame> outputQue){
        this.outputQue = outputQue;
    }
    public Queue<TagFrame> getOutputQue(){
        return outputQue;
    }
    public TagFrame handleFrame(TagFrame frame){
        return frame;
    }

    @Override
    public void run() {
        while (!exit) {
            TagFrame frame =inputQue.poll();

            if(frame == null){
                try {
                    Thread.sleep(100);
                } catch(InterruptedException e) {
                    System.err.println("Interrupted");
                }
            }

            frame = handleFrame(frame);
            if(!ignoreOutput){
                outputQue.offer(frame);
            }
        }
    }
}
