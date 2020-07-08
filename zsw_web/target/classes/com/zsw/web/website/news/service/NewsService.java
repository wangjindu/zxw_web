package com.zsw.web.website.news.service;

import com.github.pagehelper.PageInfo;
import com.zsw.web.common.CommonResult;
import com.zsw.web.website.news.entity.NewsEntity;

import java.util.List;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zsw.web.website.news.service
 * @date 2020/6/12 10:19
 */
public interface NewsService {

    CommonResult<Integer> getNewsNum();

    CommonResult<List<NewsEntity>> get7News();

    CommonResult<List<NewsEntity>> get4News();

    CommonResult<List<NewsEntity>> get4HotNews();

    CommonResult<PageInfo<NewsEntity>> get8HotNews(Integer pageNum);

    CommonResult<List<NewsEntity>> get4NewsByType(Integer newsType);

    CommonResult<PageInfo<NewsEntity>> getAllNews(Integer pageNum);

    CommonResult<PageInfo<NewsEntity>> getAllNewsByType(Integer pageNum, Integer newsType);

    CommonResult<PageInfo<NewsEntity>> getAllPcNews(Integer pageNum);

    CommonResult<NewsEntity> getNewsById(String newsId);

    CommonResult<PageInfo<NewsEntity>> getNewsByType(int newsType, Integer pageNum);

    boolean putNewsById(NewsEntity newsEntity);

    CommonResult<Integer> getNewsRedAllCount();

}
