package com.detect.controller;

import com.detect.controller.constant.ControllerConstant;
import com.detect.counter.Counter;
import com.detect.domain.CameraBO;
import com.detect.domain.CameraVO;
import com.detect.domain.ListVO;
import com.detect.domain.ResponseEntity;
import com.detect.service.DetectService;
import com.detect.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2017/12/07.
 */

@RestController
@EnableAutoConfiguration
@RequestMapping("detect")
public class DetectController {
    private static final Logger log = LoggerFactory.getLogger(DetectService.class);

    @Autowired
    DetectService detectService;

    private static String logInfo(String sessionId,String intefaceName,String json){
        return "<"+sessionId+"> "+"<"+intefaceName+"> "+"<"+json+">";
    }

    private static String errorInfo(String sessionId,String intefaceName,String errorCode,String errorMsg){
        return "<"+sessionId+"> "+"<"+intefaceName+"> fail "+"<"+errorCode+">"+"<"+errorMsg+">";
    }

    @RequestMapping("/queryCameraList")
    public ResponseEntity queryCameraList(CameraVO cameraVO, HttpServletRequest request){
        log.info(logInfo(request.getSession().getId(),
                ControllerConstant.QUERRY_CAMERA_LIST, JsonUtil.beanToJson(cameraVO)));
        Counter.incQueryCameraList();

        ResponseEntity resp = new ResponseEntity();
        List<CameraVO> cameraList = null;

        CameraBO cameraBO = new CameraBO();
        String cameraId = cameraVO.getCameraid();
        if (cameraId == null || "".equals(cameraId))
        {
            String errorCode = ControllerConstant.RESP_CODE_FAIL_PARAMISTAKE;
            log.error(errorInfo(request.getSession().getId(), ControllerConstant.QUERRY_CAMERA_LIST,
                    errorCode,ControllerConstant.RESP_MSG_PARAMISTAKE));
            resp.setSuccess(ControllerConstant.RESP_FAIL);
            resp.setCode(errorCode);
            resp.setMessage(ControllerConstant.RESP_MSG_PARAMISTAKE);
            return resp;
        }

        cameraBO.setCameraid(cameraVO.getCameraid());
        try{
            cameraList = detectService.selectCamera(cameraBO);
        }catch(Exception e){
            String errorCode = ControllerConstant.RESP_CODE_FAIL_GETDATA;
            log.error(errorInfo(request.getSession().getId(), ControllerConstant.QUERRY_CAMERA_LIST,
                    errorCode, e.getMessage()));
            resp.setSuccess(ControllerConstant.RESP_FAIL);
            resp.setCode(errorCode);
            resp.setMessage(ControllerConstant.RESP_MSG_GETDATAFAIL);
            return resp;

        }

        Counter.incQueryCameraSucc();

        if(cameraList == null || cameraList.size() <= 0){
            resp.setMessage(ControllerConstant.RESP_MSG_NODATA);
        }
        resp.setSuccess(ControllerConstant.RESP_SUCCESS);
        resp.setCode(ControllerConstant.RESP_CODE_SUCCESS);
        resp.setData(cameraList.get(0));
        return resp;

    }
}
