package com.jh.www.imgmanage.news.service;

import com.jh.www.imgmanage.domain.News;
import com.jh.www.imgmanage.domain.NewsList;
import com.jh.www.imgmanage.news.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService{
    @Autowired
    private NewsMapper newsMapper;

    @Override
    public List<NewsList> selectAllNews(String id){
        return newsMapper.selectAllNews(id);
    }

    @Override
    public List<NewsList> selectByNewsId(String id) {
        return newsMapper.selectByNewsId(id);
    }

    @Override
    public int insertNews(News news) {
        return newsMapper.insertNews(news);
    }

    @Override
    public int updateNews(News news) {
        return newsMapper.updateNews(news);
    }

    @Override
    public int deleteNews(String id) {
        return newsMapper.deleteNews(id);
    }

}
