package com.zsw.web.basic.sign.controller;

import com.zsw.web.basic.sign.entity.SignEntity;
import com.zsw.web.basic.sign.service.SignService;
import com.zsw.web.common.CommonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zsw.web.basic.sign.controller
 * @date 2020/6/28 18:18
 */
@RestController
@RequestMapping(value = "/sign")
public class SignController {

    @Resource
    private SignService signService;

    @PostMapping(value = "/postSign")
    public CommonResult postSign(@RequestBody SignEntity signEntity){
        return signService.postSign(signEntity);
    }
}
