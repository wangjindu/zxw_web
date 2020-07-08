package com.zsw.web.common;

import com.zsw.web.search.service.SearchService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zsw.web.common
 * @date 2020/6/22 16:23
 */
@Component
@Order(value = 1)
public class StartService implements ApplicationRunner {

    @Resource
    private SearchService searchService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        boolean result = searchService.addDataToEs();
        if (result){
            System.out.println("=====操作ES成功=====");
        }else {System.out.println("=====操作ES失败=====");}

    }
}
