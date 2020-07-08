package com.zsw.web.basic.letter.sevice;

import com.zsw.web.basic.letter.entity.LetterEntity;
import com.zsw.web.common.CommonResult;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zsw.web.basic.letter.sevice
 * @date 2020/6/14 14:00
 */
public interface LetterService {

    CommonResult<Integer> postLetter(LetterEntity letterEntity);

    CommonResult<Integer> getCountLetter();
}
