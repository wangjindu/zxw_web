package com.zsw.web.basic.phone.service;

import com.zsw.web.basic.phone.entity.PhoneEntity;
import com.zsw.web.basic.phone.mapper.PhoneMapper;
import com.zsw.web.common.CommonResult;
import com.zsw.web.common.Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zsw.web.basic.phone.service
 * @date 2020/6/16 16:46
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PhoneServiceImpl implements PhoneService {

    @Resource
    private PhoneMapper phoneMapper;
    @Resource
    private Utils utils;

    @Override
    public CommonResult<Integer> postPhone(String phoneNum) {
        if (null == phoneNum){
            return new CommonResult<>(400,"传入数据有误");
        }
        if (utils.isMobile(phoneNum)){
            return new CommonResult<>(400,"请输入正确手机号");
        }
        String phoneId = utils.getUUID();
        Date addTime = new Date();
        PhoneEntity phoneEntity = new PhoneEntity(phoneId, phoneNum, addTime);
        Integer result;
        try {
            result = phoneMapper.postPhone(phoneEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult<>(400,"添加失败");
        }
        if (result > 0){
            return new CommonResult<>(200,"添加成功");
        }else {
            return new CommonResult<>(400,"添加失败");
        }
    }
}
