package com.zsw.web.website.news.mapper;

import com.zsw.web.website.news.entity.NewsEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 王金都
 * @version V1.0
 * @Package com.zsw.web.website.news.mapper
 * @date 2020/6/12 10:10
 */
@Mapper
public interface NewsMapper {

    Integer getNewsNum();

    List<NewsEntity> get7News();

    List<NewsEntity> get4News();

    List<NewsEntity> get4HotNews();

    List<NewsEntity> get8HotNews();

    List<NewsEntity> get4NewsByType(Integer newsType);


    List<NewsEntity> getAllNews();

    List<NewsEntity> getAllNewsByType(Integer newsType);

    NewsEntity getNewsById(String newsId);

    Integer putNewsById(NewsEntity newsEntity);

    List<NewsEntity> getNewsByType(int newsType);

    Integer getNewsRedAllCount();

    List<NewsEntity> getAllNewsForSearch();
}
