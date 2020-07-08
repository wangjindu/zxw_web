package com.zsw.web.basic.sign.service;

import com.zsw.web.basic.sign.entity.SignEntity;
import com.zsw.web.common.CommonResult;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zsw.web.basic.sign.service
 * @date 2020/6/28 18:12
 */
public interface SignService {

    CommonResult<Integer> postSign(SignEntity signEntity);
}
