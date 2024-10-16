package com.jh.www.imgmanage.news.mapper;

import com.jh.www.imgmanage.domain.News;
import com.jh.www.imgmanage.domain.NewsList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewsMapper {
    List<NewsList> selectAllNews(String id);
    List<NewsList> selectByNewsId(String id);
    int insertNews(News news);
    int updateNews(News news);
    int deleteNews(String id);
}
