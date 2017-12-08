package com.detect.service;

import com.detect.domain.*;

import java.util.List;

/**
 * Created by Administrator on 2017/12/07.
 */
public interface DetectService {
    /** cameral operation**/
    ListVO selectCamera(CameraBO cameraBO);
    int insertCamera(CameraBO cameraBO);
    int updateCamera(CameraBO cameraBO);
    int deleteCamera(CameraBO cameraBO);

    /** ctl operation**/
    ListVO selectCtl(CtlBO ctlBO);
    int insertCtl(CtlBO ctlBO);
    int updateCtl(CtlBO ctlBO);
    int deleteCtl(CtlBO ctlBO);

    /** ctl operation**/
    ListVO selectExec(ExecBO execBO);
    int insertExec(ExecBO execBO);
    int updateExec(ExecBO execBO);
    int deleteExec(ExecBO execBO);

    /** proc operation**/
    ListVO selectProc(ProcBO procBO);
    int insertProc(ProcBO procBO);
    int updateProc(ProcBO procBO);
    int deleteProc(ProcBO procBO);

    /** sch operation**/
    ListVO selectSch(SchBO schBO);
    int insertSch(SchBO schBO);
    int updateSch(SchBO schBO);
    int deleteSch(SchBO schBO);
}
