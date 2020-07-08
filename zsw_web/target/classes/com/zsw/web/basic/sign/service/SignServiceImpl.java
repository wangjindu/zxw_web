package com.zsw.web.basic.sign.service;

import com.zsw.web.basic.sign.entity.SignEntity;
import com.zsw.web.basic.sign.mapper.SignMapper;
import com.zsw.web.common.CommonResult;
import com.zsw.web.common.Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zsw.web.basic.sign.service
 * @date 2020/6/28 18:13
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SignServiceImpl implements SignService {

    @Resource
    private SignMapper signMapper;
    @Resource
    private Utils utils;

    @Override
    public CommonResult<Integer> postSign(SignEntity signEntity) {
        if (null == signEntity){
            return new CommonResult<>(400,"传入数据有误");
        }
        if (utils.isMobile(signEntity.getSignPhone())){
            return new CommonResult<>(400,"请输入正确手机号");
        }
        Integer result;
        try {
            String singId = utils.getUUID();
            signEntity.setAddTime(new Date());
            signEntity.setSignId(singId);
            result = signMapper.postSign(signEntity);
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
