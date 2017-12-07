package com.detect.service;

import com.detect.domain.CameraBO;
import com.detect.domain.CameraDO;
import com.detect.domain.CameraVO;

import java.util.List;

/**
 * Created by Administrator on 2017/12/07.
 */
public interface DetectService {
    /**
     * @Description: get cameral info
     * @author
     * @date
     */
    List<CameraVO> selectCamera(CameraBO cameraBO);
}
