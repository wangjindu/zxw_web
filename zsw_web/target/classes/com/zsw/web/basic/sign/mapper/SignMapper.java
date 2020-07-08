package com.zsw.web.basic.sign.mapper;

import com.zsw.web.basic.sign.entity.SignEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zsw.web.basic.sign.mapper
 * @date 2020/6/28 18:09
 */
@Mapper
public interface SignMapper {

    Integer postSign(SignEntity signEntity);
}
