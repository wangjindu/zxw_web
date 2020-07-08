package com.zsw.web.basic.letter.controller;

import com.zsw.web.basic.letter.entity.LetterEntity;
import com.zsw.web.basic.letter.sevice.LetterService;
import com.zsw.web.common.CommonResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zsw.web.basic.letter.controller
 * @date 2020/6/14 14:15
 */
@RestController
@RequestMapping(value = "/letter")
public class LetterController {

    @Resource
    private LetterService letterService;

    @PostMapping(value = "/postLetter")
    public CommonResult<Integer> postLetter(@RequestBody LetterEntity letterEntity){
        return letterService.postLetter(letterEntity);
    }

    @GetMapping(value = "/getCountLetter")
    public CommonResult<Integer> getCountLetter(){
        return letterService.getCountLetter();
    }
}
