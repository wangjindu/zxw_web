package com.zsw.web.search.controller;

import com.zsw.web.search.service.SearchService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zsw.web.search.controller
 * @date 2020/6/22 11:23
 */
@RestController
@RequestMapping(value = "/search")
public class SearchController {

    @Resource
    private SearchService searchService;

    @GetMapping(value = "/searchFromEs/{keyWord}")
    public List<Map<String,Object>> searchFromEs(@PathVariable(value = "keyWord") String keyWord) throws IOException {
        return searchService.searchFromEs(keyWord);
    }

}
