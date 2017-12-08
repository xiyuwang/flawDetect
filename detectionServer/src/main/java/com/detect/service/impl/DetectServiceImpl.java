package com.detect.service.impl;

import com.detect.constant.GlobalConstant;
import com.detect.dao.DetectMapper;
import com.detect.domain.*;
import com.detect.service.DetectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/07.
 */
@Service(value="detectService")
public class DetectServiceImpl implements DetectService {
    @Autowired
    DetectMapper mapper;

    @Override
    public ListVO selectCamera(CameraBO cameraBO){
        CameraDO cameraDo= new CameraDO();
        cameraDo.setCamid(cameraBO.getCamid());
        cameraDo.setStatus(cameraBO.getStatus());
        cameraDo.setExposuretime(cameraBO.getExposuretime());

        ListVO listVO = new ListVO();
        int count = mapper.selectCameraCount(cameraDo);
        listVO.setCount(count);

        if(count <= 0){
            listVO.setData(new ArrayList<CameraVO>());
            return listVO;
        }

        List<CameraVO> cameraList = mapper.selectCamera(cameraDo);
        listVO.setData(cameraList);
        return listVO;
    }
    @Override
    public int insertCamera(CameraBO cameraBO){
        CameraDO cameraDo= new CameraDO();
        cameraDo.setCamid(cameraBO.getCamid());

        boolean bExist = mapper.existCamera(cameraDo);
        if(bExist){
            return GlobalConstant.ERR_SRV_DATAEXIST;
        }
        cameraDo.setStatus(cameraBO.getStatus());
        cameraDo.setExposuretime(cameraBO.getExposuretime());
        return mapper.insertCamera(cameraDo);
    }
    @Override
    public int updateCamera(CameraBO cameraBO){
        CameraDO cameraDo= new CameraDO();
        cameraDo.setCamid(cameraBO.getCamid());

        boolean bExist = mapper.existCamera(cameraDo);
        if(!bExist){
            return GlobalConstant.ERR_SRV_NODATA;
        }

        cameraDo.setExposuretime(cameraBO.getExposuretime());
        cameraDo.setStatus(cameraBO.getStatus());
        return mapper.updateCamera(cameraDo);
    }
    @Override
    public int deleteCamera(CameraBO cameraBO){
        CameraDO cameraDo= new CameraDO();
        cameraDo.setCamid(cameraBO.getCamid());

        boolean bExist = mapper.existCamera(cameraDo);
        if(!bExist){
            return GlobalConstant.ERR_SRV_NODATA;
        }

        cameraDo.setExposuretime(cameraBO.getExposuretime());
        cameraDo.setStatus(cameraBO.getStatus());
        return mapper.deleteCamera(cameraDo);
    }

    @Override
    public ListVO selectCtl(CtlBO ctlBO){
        CtlDO ctlDo= new CtlDO();
        ctlDo.setCtlid(ctlBO.getCtlid());

        ListVO listVO = new ListVO();
        int count = mapper.selectCtlCount(ctlDo);
        listVO.setCount(count);

        if(count <= 0){
            listVO.setData(new ArrayList<CtlVO>());
            return listVO;
        }

        List<CtlVO> ctlList = mapper.selectCtl(ctlDo);
        listVO.setData(ctlList);
        return listVO;
    }
    @Override
    public int insertCtl(CtlBO ctlBO){
        CtlDO ctlDo= new CtlDO();
        ctlDo.setCtlid(ctlBO.getCtlid());

        boolean bExist = mapper.existCtl(ctlDo);
        if(bExist){
            return GlobalConstant.ERR_SRV_DATAEXIST;
        }
        return mapper.insertCtl(ctlDo);
    }
    @Override
    public int updateCtl(CtlBO ctlBO){
        CtlDO ctlDo= new CtlDO();
        ctlDo.setCtlid(ctlBO.getCtlid());

        boolean bExist = mapper.existCtl(ctlDo);
        if(!bExist){
            return GlobalConstant.ERR_SRV_NODATA;
        }

        ctlDo.setCtlid(ctlBO.getCtlid());
        return mapper.updateCtl(ctlDo);
    }
    @Override
    public int deleteCtl(CtlBO ctlBO){
        CtlDO ctlDo= new CtlDO();
        ctlDo.setCtlid(ctlBO.getCtlid());

        boolean bExist = mapper.existCtl(ctlDo);
        if(!bExist){
            return GlobalConstant.ERR_SRV_NODATA;
        }

        ctlDo.setCtlid(ctlBO.getCtlid());
        return mapper.deleteCtl(ctlDo);
    }


    @Override
    public ListVO selectExec(ExecBO execBO){
        ExecDO execDo= new ExecDO();
        execDo.setExecid(execBO.getExecid());
        execDo.setCtlid(execBO.getCtlid());
        execDo.setCamid(execBO.getCamid());
        execDo.setStatus(execBO.getStatus());

        ListVO listVO = new ListVO();
        int count = mapper.selectExecCount(execDo);
        listVO.setCount(count);

        if(count <= 0){
            listVO.setData(new ArrayList<ExecVO>());
            return listVO;
        }

        List<ExecVO> execList = mapper.selectExec(execDo);
        listVO.setData(execList);
        return listVO;
    }
    @Override
    public int insertExec(ExecBO execBO){
        ExecDO execDo= new ExecDO();
        execDo.setExecid(execBO.getExecid());

        boolean bExist = mapper.existExec(execDo);
        if(bExist){
            return GlobalConstant.ERR_SRV_DATAEXIST;
        }
        execDo.setCtlid(execBO.getCtlid());
        execDo.setCamid(execBO.getCamid());
        execDo.setStatus(execBO.getStatus());
        return mapper.insertExec(execDo);
    }
    @Override
    public int updateExec(ExecBO execBO){
        ExecDO execDo= new ExecDO();
        execDo.setExecid(execBO.getExecid());

        boolean bExist = mapper.existExec(execDo);
        if(!bExist){
            return GlobalConstant.ERR_SRV_NODATA;
        }

        execDo.setCtlid(execBO.getCtlid());
        execDo.setCamid(execBO.getCamid());
        execDo.setStatus(execBO.getStatus());
        return mapper.updateExec(execDo);
    }
    @Override
    public int deleteExec(ExecBO execBO){
        ExecDO execDo= new ExecDO();
        execDo.setExecid(execBO.getExecid());

        boolean bExist = mapper.existExec(execDo);
        if(!bExist){
            return GlobalConstant.ERR_SRV_NODATA;
        }

        execDo.setCtlid(execBO.getCtlid());
        execDo.setCamid(execBO.getCamid());
        execDo.setStatus(execBO.getStatus());
        return mapper.deleteExec(execDo);
    }

    @Override
    public ListVO selectProc(ProcBO procBO){
        ProcDO procDo= new ProcDO();
        procDo.setProcid(procBO.getProcid());
        procDo.setExecid(procBO.getExecid());
        procDo.setType(procBO.getType());
        procDo.setFilename(procBO.getFilename());

        ListVO listVO = new ListVO();
        int count = mapper.selectProcCount(procDo);
        listVO.setCount(count);

        if(count <= 0){
            listVO.setData(new ArrayList<ProcVO>());
            return listVO;
        }

        List<ProcVO> procList = mapper.selectProc(procDo);
        listVO.setData(procList);
        return listVO;
    }
    @Override
    public int insertProc(ProcBO procBO){
        ProcDO procDo= new ProcDO();
        procDo.setProcid(procBO.getProcid());

        boolean bExist = mapper.existProc(procDo);
        if(bExist){
            return GlobalConstant.ERR_SRV_DATAEXIST;
        }
        procDo.setExecid(procBO.getExecid());
        procDo.setType(procBO.getType());
        procDo.setFilename(procBO.getFilename());
        return mapper.insertProc(procDo);
    }
    @Override
    public int updateProc(ProcBO procBO){
        ProcDO procDo= new ProcDO();
        procDo.setProcid(procBO.getProcid());

        boolean bExist = mapper.existProc(procDo);
        if(!bExist){
            return GlobalConstant.ERR_SRV_NODATA;
        }

        procDo.setExecid(procBO.getExecid());
        procDo.setType(procBO.getType());
        procDo.setFilename(procBO.getFilename());
        return mapper.updateProc(procDo);
    }
    @Override
    public int deleteProc(ProcBO procBO){
        ProcDO procDo= new ProcDO();
        procDo.setProcid(procBO.getProcid());

        boolean bExist = mapper.existProc(procDo);
        if(!bExist){
            return GlobalConstant.ERR_SRV_NODATA;
        }

        procDo.setExecid(procBO.getExecid());
        procDo.setType(procBO.getType());
        procDo.setFilename(procBO.getFilename());
        return mapper.deleteProc(procDo);
    }

    @Override
    public ListVO selectSch(SchBO schBO){
        SchDO schDo= new SchDO();
        schDo.setSchid(schBO.getSchid());
        schDo.setExecid(schBO.getExecid());
        schDo.setStatus(schBO.getStatus());
        schDo.setStoptime(schBO.getStoptime());

        ListVO listVO = new ListVO();
        int count = mapper.selectSchCount(schDo);
        listVO.setCount(count);

        if(count <= 0){
            listVO.setData(new ArrayList<SchVO>());
            return listVO;
        }

        List<SchVO> schList = mapper.selectSch(schDo);
        listVO.setData(schList);
        return listVO;
    }
    @Override
    public int insertSch(SchBO schBO){
        SchDO schDo= new SchDO();
        schDo.setSchid(schBO.getSchid());

        boolean bExist = mapper.existSch(schDo);
        if(bExist){
            return GlobalConstant.ERR_SRV_DATAEXIST;
        }
        schDo.setExecid(schBO.getExecid());
        schDo.setStatus(schBO.getStatus());
        schDo.setStoptime(schBO.getStoptime());
        return mapper.insertSch(schDo);
    }
    @Override
    public int updateSch(SchBO schBO){
        SchDO schDo= new SchDO();
        schDo.setSchid(schBO.getSchid());

        boolean bExist = mapper.existSch(schDo);
        if(!bExist){
            return GlobalConstant.ERR_SRV_NODATA;
        }

        schDo.setExecid(schBO.getExecid());
        schDo.setStatus(schBO.getStatus());
        schDo.setStoptime(schBO.getStoptime());
        return mapper.updateSch(schDo);
    }
    @Override
    public int deleteSch(SchBO schBO){
        SchDO schDo= new SchDO();
        schDo.setSchid(schBO.getSchid());

        boolean bExist = mapper.existSch(schDo);
        if(!bExist){
            return GlobalConstant.ERR_SRV_NODATA;
        }

        schDo.setExecid(schBO.getExecid());
        schDo.setStatus(schBO.getStatus());
        schDo.setStoptime(schBO.getStoptime());
        return mapper.deleteSch(schDo);
    }
}
