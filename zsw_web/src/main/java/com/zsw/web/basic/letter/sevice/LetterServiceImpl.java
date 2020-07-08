package com.zsw.web.basic.letter.sevice;

import com.zsw.web.basic.letter.entity.LetterEntity;
import com.zsw.web.basic.letter.mapper.LetterMapper;
import com.zsw.web.common.CommonResult;
import com.zsw.web.common.Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zsw.web.basic.letter.sevice
 * @date 2020/6/14 14:00
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LetterServiceImpl implements LetterService {

    @Resource
    private LetterMapper letterMapper;
    @Resource
    private Utils utils;


    @Override
    public CommonResult<Integer> postLetter(LetterEntity letterEntity) {
        if (null == letterEntity){
            return new CommonResult<>(400, "发送数据失败");
        }
        if (utils.isMobile(letterEntity.getLetterPhone())){
            return new CommonResult<>(400,"请输入正确手机号");
        }
        String letterId = utils.getUUID();
        letterEntity.setLetterId(letterId);
        if (0 == letterEntity.getLetterType()){
            return new CommonResult<>(400,"传入数据有误");
        }
        Date addTime = new Date();
        letterEntity.setAddTime(addTime);
        //LetterEntity letterEntity = new LetterEntity(letterId, letterType, letterContent, letterPhone ,addTime);
        Integer result;
        try {
            result = letterMapper.postLetter(letterEntity);
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

    @Override
    public CommonResult<Integer> getCountLetter() {
        Integer count;
        try {
            count = letterMapper.getCountLetter();
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult<>(400,"查询失败");
        }
        if (count > 0){
            return new CommonResult<>(200,"查到数据",count);
        }else if (0 == count){
            return new CommonResult<>(400,"未查到数据",count);
        }else {
            return new CommonResult<>(400,"查询失败");
        }
    }
}
