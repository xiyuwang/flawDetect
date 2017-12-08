package com.detect.controller;

import com.detect.constant.GlobalConstant;
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
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/12/07.
 */

@RestController
@EnableAutoConfiguration
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

        /** TODO: parameter checking **/

        ResponseEntity resp = new ResponseEntity();
        ListVO cameraList = null;

        CameraBO cameraBO = new CameraBO();
        String cameraId = cameraVO.getCameraid();
        if (cameraId == null || "".equals(cameraId))
        {
            String errorCode = GlobalConstant.RESP_CODE_FAIL_PARAMISTAKE;
            log.error(errorInfo(request.getSession().getId(), ControllerConstant.QUERRY_CAMERA_LIST,
                    errorCode,GlobalConstant.RESP_MSG_PARAMISTAKE));
            resp.setSuccess(GlobalConstant.RESP_FAIL);
            resp.setCode(errorCode);
            resp.setMessage(GlobalConstant.RESP_MSG_PARAMISTAKE);
            return resp;
        }

        cameraBO.setCameraid(cameraVO.getCameraid());
        try{
            cameraList = detectService.selectCamera(cameraBO);
        }catch(Exception e){
            String errorCode = GlobalConstant.RESP_CODE_FAIL_GETDATA;
            log.error(errorInfo(request.getSession().getId(), ControllerConstant.QUERRY_CAMERA_LIST,
                    errorCode, e.getMessage()));
            resp.setSuccess(GlobalConstant.RESP_FAIL);
            resp.setCode(errorCode);
            resp.setMessage(GlobalConstant.RESP_MSG_GETDATAFAIL);
            return resp;

        }

        Counter.incQueryCameraSucc();

        if(cameraList == null || cameraList.getCount() <= 0){
            resp.setMessage(GlobalConstant.RESP_MSG_NODATA);
        }
        resp.setSuccess(GlobalConstant.RESP_SUCCESS);
        resp.setCode(GlobalConstant.RESP_CODE_SUCCESS);
        resp.setData(cameraList);
        return resp;

    }

    @RequestMapping("/insertCamera")
    public ResponseEntity insertCamera(CameraVO cameraVO, HttpServletRequest request){
        log.info(logInfo(request.getSession().getId(),
                ControllerConstant.INSERT_CAMERA, JsonUtil.beanToJson(cameraVO)));
        Counter.incInsertCamera();

        ResponseEntity resp = new ResponseEntity();

        /** TODO: parameter checking **/

        CameraBO cameraBO = new CameraBO();
        cameraBO.setCameraid(cameraVO.getCameraid());
        cameraBO.setExposuretime(cameraVO.getExposuretime());
        cameraBO.setStatus(cameraVO.getStatus());

        int info = -1;
        try {
            info = detectService.insertCamera(cameraBO);
        } catch (Exception e) {

            String errorCode = GlobalConstant.RESP_CODE_FAIL_INSERTDATA;
            log.error(errorInfo(request.getSession().getId(), GlobalConstant.RESP_CODE_FAIL_INSERTDATA,
                    errorCode, e.getMessage()));
            resp.setSuccess(GlobalConstant.RESP_FAIL);
            resp.setCode(errorCode);
            resp.setMessage(GlobalConstant.RESP_MSG_INSERTDATAFAIL);
            return resp;

        }

        if(info == GlobalConstant.ERR_SRV_DATAEXIST){
            String errorCode = GlobalConstant.RESP_CODE_FAIL_SRV_DATAEXIST;
            log.error(errorInfo(request.getSession().getId(), GlobalConstant.RESP_CODE_FAIL_INSERTDATA,
                    errorCode, ""));
            resp.setSuccess(GlobalConstant.RESP_FAIL);
            resp.setCode(errorCode);
            resp.setMessage(GlobalConstant.RESP_MSG_INSERTDATAFAIL);
            return resp;
        }

        Counter.incInsertCameraSucc();
        resp.setSuccess(GlobalConstant.RESP_SUCCESS);
        resp.setCode(GlobalConstant.RESP_CODE_SUCCESS);
        return resp;
    }

    @RequestMapping("/updateCamera")
    public ResponseEntity updateCamera(CameraVO cameraVO, HttpServletRequest request){
        log.info(logInfo(request.getSession().getId(),
                ControllerConstant.UPDATE_CAMERA, JsonUtil.beanToJson(cameraVO)));
        Counter.incInsertCamera();

        ResponseEntity resp = new ResponseEntity();

        /** TODO: parameter checking **/

        CameraBO cameraBO = new CameraBO();
        cameraBO.setCameraid(cameraVO.getCameraid());
        cameraBO.setExposuretime(cameraVO.getExposuretime());
        cameraBO.setStatus(cameraVO.getStatus());

        int info = -1;
        try {
            info = detectService.updateCamera(cameraBO);
        } catch (Exception e) {

            String errorCode = GlobalConstant.RESP_CODE_FAIL_UPDATEDATA;
            log.error(errorInfo(request.getSession().getId(), GlobalConstant.RESP_CODE_FAIL_UPDATEDATA,
                    errorCode, e.getMessage()));
            resp.setSuccess(GlobalConstant.RESP_FAIL);
            resp.setCode(errorCode);
            resp.setMessage(GlobalConstant.RESP_MSG_UPDATEDATAFAIL);
            return resp;

        }

        if(info == GlobalConstant.ERR_SRV_NODATA){
            String errorCode = GlobalConstant.RESP_CODE_FAIL_SRV_NODATA;
            log.error(errorInfo(request.getSession().getId(), GlobalConstant.RESP_CODE_FAIL_UPDATEDATA,
                    errorCode, ""));
            resp.setSuccess(GlobalConstant.RESP_FAIL);
            resp.setCode(errorCode);
            resp.setMessage(GlobalConstant.RESP_MSG_UPDATEDATAFAIL);
            return resp;
        }

        Counter.incInsertCameraSucc();
        resp.setSuccess(GlobalConstant.RESP_SUCCESS);
        resp.setCode(GlobalConstant.RESP_CODE_SUCCESS);
        return resp;
    }

    @RequestMapping("/removeCamera")
    public ResponseEntity removeCamera(CameraVO cameraVO, HttpServletRequest request){
        log.info(logInfo(request.getSession().getId(),
                ControllerConstant.REMOVE_CAMERA, JsonUtil.beanToJson(cameraVO)));
        Counter.incInsertCamera();

        ResponseEntity resp = new ResponseEntity();

        /** TODO: parameter checking **/

        CameraBO cameraBO = new CameraBO();
        cameraBO.setCameraid(cameraVO.getCameraid());
        cameraBO.setExposuretime(cameraVO.getExposuretime());
        cameraBO.setStatus(cameraVO.getStatus());

        int info = -1;
        try {
            info = detectService.updateCamera(cameraBO);
        } catch (Exception e) {

            String errorCode = GlobalConstant.RESP_CODE_FAIL_DELETEDATA;
            log.error(errorInfo(request.getSession().getId(), GlobalConstant.RESP_CODE_FAIL_DELETEDATA,
                    errorCode, e.getMessage()));
            resp.setSuccess(GlobalConstant.RESP_FAIL);
            resp.setCode(errorCode);
            resp.setMessage(GlobalConstant.RESP_MSG_DELETEDATAFAIL);
            return resp;

        }

        if(info == GlobalConstant.ERR_SRV_NODATA){
            String errorCode = GlobalConstant.RESP_CODE_FAIL_SRV_NODATA;
            log.error(errorInfo(request.getSession().getId(), GlobalConstant.RESP_CODE_FAIL_DELETEDATA,
                    errorCode, ""));
            resp.setSuccess(GlobalConstant.RESP_FAIL);
            resp.setCode(errorCode);
            resp.setMessage(GlobalConstant.RESP_MSG_DELETEDATAFAIL);
            return resp;
        }

        Counter.incInsertCameraSucc();
        resp.setSuccess(GlobalConstant.RESP_SUCCESS);
        resp.setCode(GlobalConstant.RESP_CODE_SUCCESS);
        return resp;
    }
}
