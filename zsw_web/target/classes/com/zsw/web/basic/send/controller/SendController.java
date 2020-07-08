package com.zsw.web.basic.send.controller;

import com.zsw.web.basic.send.entity.SendEntity;
import com.zsw.web.basic.send.service.SendService;
import com.zsw.web.common.CommonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zsw.web.basic.send.controller
 * @date 2020/6/12 15:38
 */
@RestController
@RequestMapping(value = "/send")
public class SendController {

    @Resource
    private SendService sendService;

    @PostMapping(value = "/postSend")
    public CommonResult<Integer> postSend(@RequestBody SendEntity sendEntity){
        return sendService.postSend(sendEntity);
    }
}
