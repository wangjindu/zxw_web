package com.zsw.web.basic.send.mapper;

import com.zsw.web.basic.send.entity.SendEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zsw.web.basic.send.mapper
 * @date 2020/6/12 15:25
 */
@Mapper
public interface SendMapper {

    Integer postSend(SendEntity sendEntity);
}
