package com.zsw.web.basic.send.service;

import com.zsw.web.basic.send.entity.SendEntity;
import com.zsw.web.basic.send.mapper.SendMapper;
import com.zsw.web.common.CommonResult;
import com.zsw.web.common.Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zsw.web.basic.send.service
 * @date 2020/6/12 15:28
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SendServiceImpl implements SendService{

    @Resource
    private SendMapper sendMapper;
    @Resource
    private Utils utils;

    @Override
    public CommonResult<Integer> postSend(SendEntity sendEntity) {
        if (null == sendEntity){
            return new CommonResult<>(400,"传入数据有误");
        }
        if (utils.isMobile(sendEntity.getSendPhone())){
            return new CommonResult<>(400,"请输入正确手机号");
        }
        String sendId = utils.getUUID();
        Date addTime = new Date();
        sendEntity.setSendId(sendId);
        sendEntity.setAddTime(addTime);
        Integer result;
        try {
            result = sendMapper.postSend(sendEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult<>(400,"插入失败");
        }
        if (result > 0){
            return new CommonResult<>(200,"插入成功");
        }else {
            return new CommonResult<>(400,"插入失败");
        }
    }
}
