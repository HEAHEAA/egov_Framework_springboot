package com.jh.www.imgmanage.news.service;

import com.jh.www.imgmanage.domain.News;
import com.jh.www.imgmanage.domain.NewsList;

import java.util.List;

public interface NewsService {
    List<NewsList> selectAllNews(String id);
    List<NewsList> selectByNewsId(String id);
    int insertNews(News news);
    int updateNews(News news);
    int deleteNews(String id);
}
