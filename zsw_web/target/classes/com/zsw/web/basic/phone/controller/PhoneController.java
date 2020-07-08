package com.zsw.web.basic.phone.controller;

import com.zsw.web.basic.phone.service.PhoneService;
import com.zsw.web.common.CommonResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zsw.web.basic.phone.controller
 * @date 2020/6/16 16:53
 */
@RestController
@RequestMapping(value = "/phone")
public class PhoneController {

    @Resource
    private PhoneService phoneService;

    @PostMapping(value = "/postPhone/{phoneNum}")
    public CommonResult postPhone(@PathVariable(value = "phoneNum") String phoneNum){
        return phoneService.postPhone(phoneNum);
    }
}
