package com.zsw.web.website.banner.controller;

import com.zsw.web.common.CommonResult;
import com.zsw.web.website.banner.entity.BannerEntity;
import com.zsw.web.website.banner.service.BannerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/banner")
public class BannerController {

    @Resource
    private BannerService bannerService;

    @GetMapping(value = "/getAllPhBanner")
    public CommonResult<List<BannerEntity>> getAllPhBanner(){
        return bannerService.getAllPhBanner();
    }

    @GetMapping(value = "/getAllPcBanner")
    public CommonResult<List<BannerEntity>> getAllPcBanner(){
        return bannerService.getAllPcBanner();
    }

}
