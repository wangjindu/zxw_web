package com.zsw.web.basic.letter.mapper;

import com.zsw.web.basic.letter.entity.LetterEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zsw.web.basic.letter.mapper
 * @date 2020/6/14 13:54
 */
@Mapper
public interface LetterMapper {

    Integer postLetter(LetterEntity letterEntity);

    Integer  getCountLetter();
}
