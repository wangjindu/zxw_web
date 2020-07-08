package com.zsw.web.common;

import com.zsw.web.search.service.SearchService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zsw.web.common
 * @date 2020/6/22 16:30
 */
@Component
public class SchedulerTask {

    @Resource
    private SearchService searchService;

    // 定时任务每天0点执行一次
    @Scheduled(cron = "0 0 8,10,12,14,16,20 * * ?")
    private void process() throws IOException {
        System.out.println("时间到!刷新es");
        boolean result = searchService.addDataToEs();
        if (result){
            System.out.println("刷新es成功");
        }else {
            System.out.println("刷新es失败");
        }

    }

}
