package com.detect.service.impl;

import com.detect.dao.DetectMapper;
import com.detect.domain.CameraBO;
import com.detect.domain.CameraDO;
import com.detect.domain.CameraVO;
import com.detect.service.DetectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.List;

/**
 * Created by Administrator on 2017/12/07.
 */
@Service(value="detectService")
public class DetectServiceImpl implements DetectService {
    @Autowired
    DetectMapper mapper;

    /**
     * @Description: 新增会
     * @author qing.chen
     * @date 2017年7月24日 上午10:22:20
     */
    @Override
    public List<CameraVO> selectCamera(CameraBO cameraBO){
        CameraDO CameraDo= new CameraDO();
        CameraDo.setCameraid(cameraBO.getCameraid());

        return mapper.selectCamera(CameraDo);
    }

}
