package com.zsw.web.website.banner.service;

import com.zsw.web.common.CommonResult;
import com.zsw.web.website.banner.entity.BannerEntity;
import com.zsw.web.website.banner.mapper.BannerMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class BannerServiceImpl implements BannerService {

    @Resource
    private BannerMapper bannerMapper;

    @Override
    public CommonResult<List<BannerEntity>> getAllPhBanner(){
        List<BannerEntity> list;
        try {
            list = bannerMapper.getAllPhBanner();
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult<>(400,"查询失败");
        }
        return getListCommonResult(list);
    }

    private CommonResult<List<BannerEntity>> getListCommonResult(List<BannerEntity> list) {
        if (null != list) {
                for (BannerEntity bannerEntity : list) {
                    bannerEntity.setImg("http://192.168.1.114/test/"+bannerEntity.getBannerIcon());
                }

            return new CommonResult<>(200,"查到数据", list);
        }else {
            return new CommonResult<>(400,"未查到数据");
        }
    }

    @Override
    public CommonResult<List<BannerEntity>> getAllPcBanner(){
        List<BannerEntity> list;
        try {
            list = bannerMapper.getAllPcBanner();
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult<>(400,"查询失败");
        }
        return getListCommonResult(list);
    }
}
