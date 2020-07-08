package com.zsw.web.website.zsjz.mapper;

import com.zsw.web.website.zsjz.entity.ZsjzEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zsw.web.website.zsjz.mapper
 * @date 2020/6/11 21:25
 */
@Mapper
public interface ZsjzMapper {

    List<ZsjzEntity> getAllZsjz();

    List<ZsjzEntity> getAllPcZsjz();

    List<ZsjzEntity> get3Zsjz();

    ZsjzEntity getZsjzById(String zsjzId);

    Integer putZsjzById(ZsjzEntity zsjzEntity);

    ZsjzEntity getZsjzByType(Integer zsjzType);

    List<ZsjzEntity> getPcZsjzByType(Integer zsjzType);

    List<ZsjzEntity> getAllZsjzForSearch();
}
