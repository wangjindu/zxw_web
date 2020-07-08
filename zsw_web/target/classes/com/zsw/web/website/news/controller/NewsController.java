package com.zsw.web.website.news.controller;

import com.zsw.web.common.CommonResult;
import com.zsw.web.common.Utils;
import com.zsw.web.website.news.service.NewsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zsw.web.website.news.controller
 * @date 2020/6/12 10:28
 */
@RestController
@RequestMapping(value = "/news")
public class NewsController {

    @Resource
    private NewsService newsService;

    @GetMapping(value = "/getNewsNum")
    synchronized public CommonResult getNewsNum(){
        return newsService.getNewsNum();
    }

    @GetMapping(value = "/get7News")
    synchronized public CommonResult get7News(){
        return newsService.get7News();
    }

    @GetMapping(value = "/get4News")
    synchronized public CommonResult get4News(){
        return newsService.get4News();
    }

    @GetMapping(value = "/get4HotNews")
    public CommonResult get4HotNews(){
        return newsService.get4HotNews();
    }

    @GetMapping(value = "/get8HotNews/{pageNum}")
    public CommonResult get8HotNews(@PathVariable("pageNum") Integer pageNum){
        return newsService.get8HotNews(pageNum);
    }

    @GetMapping(value = "/get4NewsByType/{e}")
    public CommonResult get4InfoNews(@PathVariable("e") Integer newsType){
        return newsService.get4NewsByType(newsType);
    }

    @GetMapping(value = "/getAllNews/{pageNum}")
    public CommonResult getAllNews(@PathVariable(value = "pageNum")Integer pageNum){
        return newsService.getAllNews(pageNum);
    }

    @GetMapping(value = "/getAllNewsByType/{pageNum}/{newsType}")
    public CommonResult getAllNewsByType(@PathVariable(value = "pageNum")Integer pageNum,
                                         @PathVariable(value = "newsType")Integer newsType){
        return newsService.getAllNewsByType(pageNum,newsType);
    }

    @GetMapping(value = "/getAllPcNews/{pageNum}")
    public CommonResult getAllPcNews(@PathVariable(value = "pageNum")Integer pageNum){
        return newsService.getAllPcNews(pageNum);
    }

    @GetMapping(value = "/getNewsById/{newsId}")
    public CommonResult getNewsById(@PathVariable(value = "newsId") String newsId){
        return newsService.getNewsById(newsId);
    }

    @GetMapping(value = "/getNewsByType/{newsType}/{pageNum}")
    public CommonResult getNewsByType(@PathVariable(value = "newsType") int newsType,
                                      @PathVariable(value = "pageNum") Integer pageNum){
        return newsService.getNewsByType(newsType,pageNum);
    }

    @GetMapping(value = "/getNewsRedAllCount")
    public CommonResult getNewsRedAllCount(){
        return newsService.getNewsRedAllCount();
    }

    @Resource
    private Utils utils;




}
