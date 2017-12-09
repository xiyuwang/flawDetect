package com.detect.controller;

import java.lang.Thread;
import java.util.Map;
import java.util.HashMap;
import com.detect.constant.GlobalConstant;
import com.detect.controller.constant.ControllerConstant;
import com.detect.counter.Counter;
import com.detect.domain.ExecBO;
import com.detect.domain.ExecVO;
import com.detect.domain.ResponseEntity;
import com.detect.service.DetectService;
import com.detect.util.ApplicationContextProvider;
import com.detect.util.Executor;
import com.detect.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wxy on 2017/12/9.
 */
@RestController
@EnableAutoConfiguration
public class ExecuteController {
    private static final Logger log = LoggerFactory.getLogger(ExecuteController.class);
    private static Map<String, Executor> execMap = new HashMap<String,Executor>();

    @Autowired
    DetectService detectService;

    private static String logInfo(String sessionId,String intefaceName,String json){
        return "<"+sessionId+"> "+"<"+intefaceName+"> "+"<"+json+">";
    }

    private static String errorInfo(String sessionId,String intefaceName,String errorCode,String errorMsg){
        return "<"+sessionId+"> "+"<"+intefaceName+"> fail "+"<"+errorCode+">"+"<"+errorMsg+">";
    }

    @RequestMapping("/start")
    public ResponseEntity startExec(ExecVO execVO, HttpServletRequest request){
        log.info(logInfo(request.getSession().getId(),
                ControllerConstant.START_EXEC, JsonUtil.beanToJson(execVO)));
        Counter.incStartExec();

        ResponseEntity resp = new ResponseEntity();

        String execid = execVO.getExecid();
        ExecBO execBO = new ExecBO();
        execBO.setExecid(execid);
        boolean bExist = detectService.existExec(execBO);
        if(!bExist){
            String errorCode = GlobalConstant.RESP_CODE_FAIL_SRV_NODATA;
            log.error(errorInfo(request.getSession().getId(), GlobalConstant.RESP_CODE_FAIL_SRV_NODATA,
                    errorCode, ""));
            resp.setSuccess(GlobalConstant.RESP_FAIL);
            resp.setCode(errorCode);
            resp.setMessage(GlobalConstant.RESP_CODE_FAIL_SRV_NODATA);
            return resp;
        }

        Executor exec=   ApplicationContextProvider.getBean("execTask", Executor.class);
        exec.setExecid(execid);
        exec.setExit(false);
        exec.start();

        /* wait to initialization finish */
        int initTime = ControllerConstant.EXECUTOR_INIT_TIME;
        try {
            while (initTime >0 && exec.getInitStep() != Executor.INIT_FINAL_STEP){
                Thread.sleep(100);
                initTime--;
            }
        } catch(InterruptedException e) {
            System.err.println("Interrupted");
        }
        if (initTime <=0){
            String errorCode = GlobalConstant.RESP_CODE_FAIL_CTL_TIMEOUT;
            log.error(errorInfo(request.getSession().getId(), GlobalConstant.RESP_CODE_FAIL_CTL_TIMEOUT,
                    errorCode, ""));
            resp.setSuccess(GlobalConstant.RESP_FAIL);
            resp.setCode(errorCode);
            resp.setMessage(GlobalConstant.RESP_CODE_FAIL_CTL_TIMEOUT);
            return resp;
        }

        execMap.put(execid, exec);

        Counter.incStartExecSucc();
        resp.setSuccess(GlobalConstant.RESP_SUCCESS);
        resp.setCode(GlobalConstant.RESP_CODE_SUCCESS);
        return resp;
    }
    @RequestMapping("/stop")
    public ResponseEntity stopExec(ExecVO execVO, HttpServletRequest request){
        log.info(logInfo(request.getSession().getId(),
                ControllerConstant.STOP_EXEC, JsonUtil.beanToJson(execVO)));
        Counter.incStopExec();

        ResponseEntity resp = new ResponseEntity();

        String execid = execVO.getExecid();
        ExecBO execBO = new ExecBO();
        execBO.setExecid(execid);
        boolean bExist = execMap.containsKey(execid);
        if(!bExist){
            String errorCode = GlobalConstant.RESP_CODE_FAIL_CTL_NODATA;
            log.error(errorInfo(request.getSession().getId(), GlobalConstant.RESP_CODE_FAIL_CTL_NODATA,
                    errorCode, ""));
            resp.setSuccess(GlobalConstant.RESP_FAIL);
            resp.setCode(errorCode);
            resp.setMessage(GlobalConstant.RESP_CODE_FAIL_CTL_NODATA);
            return resp;
        }

        Executor exec=   execMap.get(execid);
        exec.setExit(true);
        execMap.remove(execid);

        Counter.incStopExecSucc();
        resp.setSuccess(GlobalConstant.RESP_SUCCESS);
        resp.setCode(GlobalConstant.RESP_CODE_SUCCESS);
        return resp;
    }
}
