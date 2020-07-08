package com.zsw.web.website.banner.mapper;

import com.zsw.web.website.banner.entity.BannerEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BannerMapper {

    List<BannerEntity> getAllPhBanner();

    List<BannerEntity> getAllPcBanner();

}
