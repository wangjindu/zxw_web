package com.zsw.web.website.zsjz.service;

import com.zsw.web.common.CommonResult;
import com.zsw.web.website.zsjz.entity.ZsjzEntity;

import java.util.List;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zsw.web.website.zsjz.service
 * @date 2020/6/11 21:28
 */
public interface ZsjzService {

    CommonResult<List<ZsjzEntity>> getAllZsjz();

    CommonResult<List<ZsjzEntity>> getAllPcZsjz();

    CommonResult<List<ZsjzEntity>> get3Zsjz();

    CommonResult<ZsjzEntity> getZsjzById(String zsjzId);

    CommonResult<ZsjzEntity> getZsjzByType(Integer zsizType);

    CommonResult<ZsjzEntity> getPcZsjzByType(Integer zsjzType);

    boolean putZsjzById(ZsjzEntity zsjzEntity);



}
