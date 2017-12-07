package com.detect.dao;

import org.apache.ibatis.annotations.Mapper;
import com.detect.domain.CameraDO;
import com.detect.domain.CameraVO;

import java.util.List;

/**
 * Created by Administrator on 2017/12/07.
 */
@Mapper
public interface DetectMapper {
    List<CameraVO> selectCamera(CameraDO cameraDo);
    int insertCamera(CameraDO cameraDo);
    int updateCamera(CameraDO cameraDo);
    int deleteCamera(CameraDO cameraDo);
}
