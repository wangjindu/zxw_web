package com.zsw.web.website.zsjz.controller;

import com.zsw.web.common.CommonResult;
import com.zsw.web.website.zsjz.entity.ZsjzEntity;
import com.zsw.web.website.zsjz.service.ZsjzService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zsw.web.website.zsjz.controller
 * @date 2020/6/11 21:31
 */
@RestController
@RequestMapping(value = "/zsjz")
public class ZsjzConreoller {

    @Resource
    private ZsjzService zsjzService;

    @GetMapping(value = "/getAllZsjz")
    public CommonResult<List<ZsjzEntity>> getAllZsjz(){
        return zsjzService.getAllZsjz();
    }

    @GetMapping(value = "/getAllPcZsjz")
    public CommonResult<List<ZsjzEntity>> getAllPcZsjz(){
        return zsjzService.getAllPcZsjz();
    }

    @GetMapping(value = "/get3Zsjz")
    public CommonResult<List<ZsjzEntity>> get3Zsjz(){
        return zsjzService.get3Zsjz();
    }

    @GetMapping(value = "/getZsjzById/{zsjzId}")
    public CommonResult<ZsjzEntity> getZsjzById(@PathVariable(value = "zsjzId") String zsjzId){
        return zsjzService.getZsjzById(zsjzId);
    }

    @GetMapping(value = "/getZsjzByType/{zsjzType}")
    public CommonResult<ZsjzEntity> getZsjzByType(@PathVariable(value = "zsjzType") Integer zsjzType){
        System.out.println(zsjzType);
        return zsjzService.getZsjzByType(zsjzType);
    }

    @GetMapping(value = "/getPcZsjzByType/{zsjzType}")
    public CommonResult<ZsjzEntity> getPcZsjzByType(@PathVariable(value = "zsjzType") Integer zsjzType){
        System.out.println(zsjzType);
        return zsjzService.getPcZsjzByType(zsjzType);
    }
}
