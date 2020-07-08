package com.zsw.web.basic.send.service;

import com.zsw.web.basic.send.entity.SendEntity;
import com.zsw.web.common.CommonResult;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zsw.web.basic.send.service
 * @date 2020/6/12 15:26
 */
public interface SendService {

    CommonResult<Integer> postSend(SendEntity sendEntity);
}
