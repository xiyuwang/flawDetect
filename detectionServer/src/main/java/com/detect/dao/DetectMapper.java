package com.detect.dao;

import com.detect.domain.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2017/12/07.
 */
@Mapper
public interface DetectMapper {
    List<CameraVO> selectCamera(CameraDO cameraDo);
    int selectCameraCount(CameraDO cameraDo);
    int insertCamera(CameraDO cameraDo);
    int updateCamera(CameraDO cameraDo);
    int deleteCamera(CameraDO cameraDo);
    boolean existCamera(CameraDO cameraDo);

    List<CtlVO> selectCtl(CtlDO ctlDo);
    int selectCtlCount(CtlDO ctlDo);
    int insertCtl(CtlDO ctlDo);
    int updateCtl(CtlDO ctlDo);
    int deleteCtl(CtlDO ctlDo);
    boolean existCtl(CtlDO ctlDo);

    List<ExecVO> selectExec(ExecDO execDo);
    int selectExecCount(ExecDO execDo);
    int insertExec(ExecDO execDo);
    int updateExec(ExecDO execDo);
    int deleteExec(ExecDO execDo);
    boolean existExec(ExecDO execDo);

    List<ProcVO> selectProc(ProcDO procDo);
    int selectProcCount(ProcDO procDo);
    int insertProc(ProcDO procDo);
    int updateProc(ProcDO procDo);
    int deleteProc(ProcDO procDo);
    boolean existProc(ProcDO procDo);

    List<SchVO> selectSch(SchDO schDo);
    int selectSchCount(SchDO schDo);
    int insertSch(SchDO schDo);
    int updateSch(SchDO schDo);
    int deleteSch(SchDO schDo);
    boolean existSch(SchDO schDo);
}
