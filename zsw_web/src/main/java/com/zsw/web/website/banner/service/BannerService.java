package com.zsw.web.website.banner.service;

import com.zsw.web.common.CommonResult;
import com.zsw.web.website.banner.entity.BannerEntity;

import java.util.List;

public interface BannerService {

    CommonResult<List<BannerEntity>> getAllPhBanner();

    CommonResult<List<BannerEntity>> getAllPcBanner();

}
