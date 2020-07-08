package com.zsw.web.basic.phone.mapper;

import com.zsw.web.basic.phone.entity.PhoneEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zsw.web.basic.phone.mapper
 * @date 2020/6/16 16:35
 */
@Mapper
public interface PhoneMapper {

    Integer postPhone(PhoneEntity phoneEntity);
}
