package com.detect.util;

import com.detect.domain.*;
import com.detect.service.DetectService;
import java.lang.Thread;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;

import org.bytedeco.javacpp.opencv_core;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.Java2DFrameConverter;

/**
 * Created by wxy on 2017/12/9.
 */
@Component("execTask")
@Scope("prototype")
public class Executor extends Thread {
    private static final Logger log = LoggerFactory.getLogger(Executor.class);
    private volatile boolean exit = false;
    private volatile int initStep;
    public final static int INIT_FINAL_STEP = 4;
    private String execid;
    private String camid;
    private List<Object> procDOList;
    private Map<String, Processor> procMap = new HashMap<String,Processor>();
    private Grabber grabber;
    private volatile Queue<TagFrame> camQue = new LinkedList<TagFrame>();

    @Autowired
    DetectService detectService;

    public void setExit(boolean exit){
        this.exit = exit;
    }
    public boolean getExit(){
        return exit;
    }
    public int getInitStep(){
        return initStep;
    }
    public void setExecid(String execid){
        this.execid = execid;
    }
    public String getExecid(){
        return execid;
    }
    public void setCamid(String camid){
        this.camid = camid;
    }
    public String getCamid(){
        return camid;
    }

    private void setProcs(){
        ProcBO procBO = new ProcBO();
        procBO.setExecid(execid);
        ListVO procList = detectService.selectProc(procBO);
        procDOList = procList.getData();
        Processor prePro = null;
        int idx = 0;
        for (Object ob:procDOList){
            ProcDO procDO = (ProcDO) ob;
            int type = procDO.getType();
            String fileName = procDO.getFilename();
            String procId = procDO.getProcid();
            Processor pro = new Processor();
            pro.setProcid(procId);
            pro.setFilename(fileName);
            pro.setType(type);
            pro.setExit(false);
            if(idx==0){
                pro.setInputQue(camQue);
            }
            else {
                pro.setInputQue(prePro.getOutputQue());
            }

            if(idx >= procDOList.size()-1){
                pro.setIgnoreOutput(true);
            }
            pro.start();
            prePro = pro;
            procMap.put(pro.getProcid(), pro);
            idx ++;
        }

    }
    private void init() throws Exception {
        /** get exec**/
        ExecBO execBO = new ExecBO();
        execBO.setExecid(execid);
        ListVO execList = detectService.selectExec(execBO);
        ExecDO execDO = ((ExecDO)(execList.getData().get(0)));
        camid = execDO.getCamid();
        initStep =1;

        /** get camera **/
        CameraBO cameraBO = new CameraBO();
        cameraBO.setCamid(camid);
        ListVO cameraList = detectService.selectCamera(cameraBO);
        initStep =2;

        /** start grabber **/
        grabber =new Grabber();
        if(Grabber.RETURN_OK != grabber.start()) return;
        initStep =3;

        /** get proc list **/
        setProcs();
        initStep =4;
    }
    public void stopProcs() throws Exception{
        for (Processor proc:procMap.values() ) {
            proc.setExit(true);
        }
        procMap.clear();
    }
    @Override
    public void run() {
        try{
            initStep =0;
            init();
        }catch(Exception e){
            return;
        }

        if(INIT_FINAL_STEP != initStep) return;

        /** grab image**/
        //OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
        //grabber.start();
        while (!exit) {
            TagFrame frame = grabber.grab();
            if(frame == null){
                continue;
            }
            camQue.offer(frame);
            //log.info("thread:" + Thread.currentThread().getName() + " "+((CameraDO)(cameraList.getData().get(0))).getCamid()+" running.....");
            log.info("thread:" + Thread.currentThread().getName() + " "+((ProcDO)(procDOList.get(0))).getExecid()+" running.....");
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                System.err.println("Interrupted");
            }
        }
    }
}
