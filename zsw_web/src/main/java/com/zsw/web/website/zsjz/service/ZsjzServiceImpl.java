package com.zsw.web.website.zsjz.service;

import com.zsw.web.common.CommonResult;
import com.zsw.web.website.zsjz.entity.ZsjzEntity;
import com.zsw.web.website.zsjz.mapper.ZsjzMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zsw.web.website.zsjz.service
 * @date 2020/6/11 21:28
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ZsjzServiceImpl implements ZsjzService {

    @Resource
    private ZsjzMapper zsjzMapper;

    @Override
    public CommonResult<List<ZsjzEntity>> getAllZsjz(){
        List<ZsjzEntity> list;
        try {
            list = zsjzMapper.getAllZsjz();
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult<>(400,"查询失败");
        }
        return getListCommonResult(list);
    }

    private CommonResult<List<ZsjzEntity>> getListCommonResult(List<ZsjzEntity> list) {
        if (null != list) {
                for (ZsjzEntity zsjzEntity : list) {
                    zsjzEntity.setImga("http://192.168.1.114/test/" + zsjzEntity.getZsjzIcona());
                }
            return new CommonResult<>(200,"查到数据", list);
        }else {
            return new CommonResult<>(400,"未查到数据");
        }
    }

    @Override
    public CommonResult<List<ZsjzEntity>> getAllPcZsjz(){
        List<ZsjzEntity> list;
        try {
            list = zsjzMapper.getAllPcZsjz();
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult<>(400,"查询失败");
        }
        if (null != list) {
                for (ZsjzEntity zsjzEntity : list) {
                    zsjzEntity.setImga("http://192.168.1.114/test/" + zsjzEntity.getZsjzIcona());
                    zsjzEntity.setImgb("http://192.168.1.114/test/" + zsjzEntity.getZsjzIconb());
                    zsjzEntity.setImgc("http://192.168.1.114/test/" + zsjzEntity.getZsjzIconc());
                    zsjzEntity.setImgd("http://192.168.1.114/test/" + zsjzEntity.getZsjzIcond());
                }
            return new CommonResult<>(200,"查到数据", list);
        }else {
            return new CommonResult<>(400,"未查到数据");
        }
    }

    @Override
    public CommonResult<List<ZsjzEntity>> get3Zsjz() {
        List<ZsjzEntity> list;
        try {
            list = zsjzMapper.get3Zsjz();
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult<>(400,"查询失败");
        }
        return getListCommonResult(list);
    }

    @Override
    public CommonResult<ZsjzEntity> getZsjzById(String zsjzId) {
        if (null == zsjzId){
            return new CommonResult<>(400,"传入数据有误");
        }
        ZsjzEntity zsjzEntity;
        try {
            zsjzEntity = zsjzMapper.getZsjzById(zsjzId);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult<>(400,"查询失败");
        }
        if (null != zsjzEntity){
            zsjzEntity.setImga("http://192.168.1.114/test/"+zsjzEntity.getZsjzIcona());
            return new CommonResult<>(200,"查询成功",zsjzEntity);
        }else {
            return new CommonResult<>(400,"查询失败");
        }
    }

    @Override
    public CommonResult<ZsjzEntity> getZsjzByType(Integer zsjzType) {
        if (null == zsjzType){
            return new CommonResult<>(400,"传入数据有误");
        }
        ZsjzEntity zsjzEntity;
        try {
            zsjzEntity = zsjzMapper.getZsjzByType(zsjzType);
        }catch (Exception e) {
            e.printStackTrace();
            return new CommonResult<>(400,"查询失败");
        }
        if (null != zsjzEntity){
//            zsjzEntity.setEnCount(zsjzEntity.getEnCount()+1);
//            boolean flag = putZsjzById(zsjzEntity);
//            if (!flag){
//                return new CommonResult<>(400,"操作数据失败");
//            }
            return new CommonResult<>(200,"查询成功",zsjzEntity);
        }else {
            return new CommonResult<>(400,"查询失败");
        }
    }

    @Override
    public CommonResult<ZsjzEntity> getPcZsjzByType(Integer zsjzType) {
        if (null == zsjzType){
            return new CommonResult<>(400,"传入数据有误");
        }
        List<ZsjzEntity> zsjzEntity;
        try {
            zsjzEntity = zsjzMapper.getPcZsjzByType(zsjzType);
        }catch (Exception e) {
            e.printStackTrace();
            return new CommonResult<>(400,"查询失败");
        }
        if (null != zsjzEntity){
            zsjzEntity.get(0).setEnCount(zsjzEntity.get(0).getEnCount()+1);
            boolean flag = putZsjzById(zsjzEntity.get(0));
            if (!flag){
                return new CommonResult<>(400,"操作数据失败");
            }
                zsjzEntity.get(0).setImga("http://192.168.1.114/test/"+zsjzEntity.get(0).getZsjzIcona());
                zsjzEntity.get(0).setImgb("http://192.168.1.114/test/"+zsjzEntity.get(0).getZsjzIconb());
            return new CommonResult<>(200,"查询成功",zsjzEntity.get(0));
        }else {
            return new CommonResult<>(400,"查询失败,数据为空");
        }
    }

    @Override
    public boolean putZsjzById(ZsjzEntity zsjzEntity) {
        if (null == zsjzEntity){
            return false;
        }
        Integer result;
        try {
            result = zsjzMapper.putZsjzById(zsjzEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return result > 0;
    }

}
