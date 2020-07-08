package com.zsw.web.website.news.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zsw.web.common.CommonResult;
import com.zsw.web.common.Utils;
import com.zsw.web.website.news.entity.NewsEntity;
import com.zsw.web.website.news.mapper.NewsMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zsw.web.website.news.service
 * @date 2020/6/12 10:20
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class NewsServiceImpl implements NewsService {

    @Resource
    private NewsMapper newsMapper;
    @Resource
    private Utils utils;

    @Override
    public CommonResult<Integer> getNewsNum() {
        Integer result;
        try {
            result = newsMapper.getNewsNum();
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult<>(400,"查询失败");
        }
        return new CommonResult<>(200,"查到数据", result);
    }

    @Override
    public CommonResult<List<NewsEntity>> get7News(){
        List<NewsEntity> list;
        try {
            list = newsMapper.get7News();
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult<>(400,"查询失败");
        }
        if (null != list) {
                for (NewsEntity newsEntity : list) {
                    newsEntity.setImg("http://192.168.1.114/test/" + newsEntity.getNewsIcon());
                }
            return new CommonResult<>(200,"查到数据", list);
        }else {
            return new CommonResult<>(400,"未查到数据");
        }
    }

    @Override
    public CommonResult<List<NewsEntity>> get4News(){
        List<NewsEntity> list;
        try {
            list = newsMapper.get4News();
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult<>(400,"查询失败");
        }
        return getListCommonResult(list);
    }

    private CommonResult<List<NewsEntity>> getListCommonResult(List<NewsEntity> list) {
        if (null != list) {
                for (NewsEntity newsEntity : list) {
                    newsEntity.setImg("http://192.168.1.114/test/" + newsEntity.getNewsIcon());
                }
            return new CommonResult<>(200,"查到数据", list);
        }else {
            return new CommonResult<>(400,"未查到数据");
        }
    }

    @Override
    public CommonResult<List<NewsEntity>> get4HotNews() {
        List<NewsEntity> list;
        try {
            list = newsMapper.get4HotNews();
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult<>(400,"查询失败");
        }
        return getListCommonResult(list);
    }

    @Override
    public CommonResult<PageInfo<NewsEntity>> get8HotNews(Integer pageNum) {
        PageHelper.startPage(pageNum,6);
        List<NewsEntity> list;
        try {
            list = newsMapper.get8HotNews();
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult<>(400,"查询失败");
        }
        if (null != list) {
                for (NewsEntity newsEntity : list) {
                    newsEntity.setImg("http://192.168.1.114/test/" + newsEntity.getNewsIcon());
                }
            PageInfo<NewsEntity> pageInfo =new PageInfo<>(list);
            return new CommonResult<>(200,"查到数据", pageInfo);
        }else {
            return new CommonResult<>(400,"未查到数据");
        }
    }

    @Override
    public CommonResult<List<NewsEntity>> get4NewsByType(Integer newsType){
        if (null == newsType){
            return new CommonResult<>(400,"传入数据有误");
        }
        List<NewsEntity> list;
        try {
            list = newsMapper.get4NewsByType(newsType);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult<>(400,"查询失败");
        }
        return getListCommonResult(list);
    }


    @Override
    public CommonResult<PageInfo<NewsEntity>> getAllNews(Integer pageNum){
        PageHelper.startPage(pageNum,6);
        return getPageInfoCommonResult();
    }

    private CommonResult<PageInfo<NewsEntity>> getPageInfoCommonResult() {
        List<NewsEntity> list;
        try {
            list = newsMapper.getAllNews();
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult<>(400,"查询失败");
        }
        return getPageInfoCommonResult(list);
    }

    private CommonResult<PageInfo<NewsEntity>> getPageInfoCommonResult(List<NewsEntity> list) {
        if (null != list) {
                for (NewsEntity newsEntity : list) {
                    newsEntity.setImg("http://192.168.1.114/test/" + newsEntity.getNewsIcon());
                }
            PageInfo<NewsEntity> pageInfo = new PageInfo<>(list);
            return new CommonResult<>(200,"查到数据", pageInfo);
        }else {
            return new CommonResult<>(400,"未查到数据");
        }
    }

    @Override
    public CommonResult<PageInfo<NewsEntity>> getAllNewsByType(Integer pageNum, Integer newsType){
        PageHelper.startPage(pageNum,8);
        if (null == newsType || 0 == newsType){
            return new CommonResult<>(400,"传入数据有误");
        }
        List<NewsEntity> list;
        try {
            list = newsMapper.getAllNewsByType(newsType);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult<>(400,"查询失败");
        }
        return getPageInfoCommonResult(list);
    }

    @Override
    public CommonResult<PageInfo<NewsEntity>> getAllPcNews(Integer pageNum){
        PageHelper.startPage(pageNum,8);
        return getPageInfoCommonResult();
    }

    @Override
    public CommonResult<NewsEntity> getNewsById(String newsId){
        if (null == newsId){
            return new CommonResult<>(400,"传入数据有误");
        }
        NewsEntity newsEntity;
        try {
            newsEntity = newsMapper.getNewsById(newsId);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult<>(400,"未查到数据");
        }
        boolean read = putNewsById(newsEntity);
        newsEntity.setImg("http://192.168.1.114/test/"+newsEntity.getNewsIcon());
        if(read){
            return new CommonResult<>(200,"查到数据",newsEntity);
        }else {
            return new CommonResult<>(400,"操作数据失败");
        }
    }

    @Override
    public CommonResult<PageInfo<NewsEntity>> getNewsByType(int newsType,Integer pageNum){
        PageHelper.startPage(pageNum,6);
        List<NewsEntity> list;
        try {
            list = newsMapper.getNewsByType(newsType);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult<>(400,"查询失败");
        }
        if (!(null == list)) {
                for (NewsEntity newsEntity : list) {
                    newsEntity.setImg("http://192.168.1.114/test/" + newsEntity.getNewsIcon());
                }
            PageInfo<NewsEntity> pageInfo =new PageInfo<>(list);
            return new CommonResult<>(200,"查到数据", pageInfo);
        }else {
            return new CommonResult<>(400,"未查到数据");
        }
    }

    /**
     * 新闻浏览次数加1
     * */
    @Override
    public boolean putNewsById(NewsEntity newsEntity) {
        newsEntity.setReadCount(newsEntity.getReadCount()+1);
        Integer result;
        try {
            result = newsMapper.putNewsById(newsEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return result > 0;
    }

    @Override
    public CommonResult<Integer> getNewsRedAllCount() {
        Integer result;
        try {
            result = newsMapper.getNewsRedAllCount();
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult<>(400,"查询失败");
        }
        if (null != result){
            return new CommonResult<>(200,"查询成功",result);
        }else {
            return new CommonResult<>(400,"查询失败");
        }
    }

}
