package com.jh.www.imgmanage.news.controller;

import com.jh.www.imgmanage.config.egovconfig.JwtTokenProvider;
import com.jh.www.imgmanage.domain.News;
import com.jh.www.imgmanage.domain.NewsList;
import com.jh.www.imgmanage.domain.ResultModel;
import com.jh.www.imgmanage.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/news/*")
public class NewsController {

    @Autowired
    NewsService newsService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @GetMapping("list/{id}")
    public ResultModel<Object> selectAllNews(HttpServletRequest request, @PathVariable String id) {
        String access_token = jwtTokenProvider.resolveToken(request);
        ResultModel<Object> resultModel = new ResultModel<>();
        int auth = jwtTokenProvider.getUserRole(access_token);

        if(auth != 1){
            resultModel.setMessage("권한이 없습니다.");
            resultModel.setSuccess(false);
            return resultModel;
        }
        resultModel.setData(newsService.selectAllNews(id));
        resultModel.setMessage("success get data");
        resultModel.setSuccess(true);
        return resultModel;
    }

    @PostMapping("insert")
    public ResultModel<Object> insertNews(HttpServletRequest request, @RequestBody News news){
        ResultModel<Object> resultModel = new ResultModel<>();
        String user = jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request));
        news.setUser_id(user);
        System.out.println(user);
        int in = newsService.insertNews(news);
        if(in == 1){
            resultModel.setSuccess(true);
            resultModel.setMessage("success insert data");
        }else{
            resultModel.setSuccess(false);
            resultModel.setMessage("fail insert data");
        }
        return resultModel;
    }

    @PostMapping("update")
    public ResultModel<Object> updateNews(HttpServletRequest request, @RequestBody News news){
        ResultModel<Object> resultModel = new ResultModel<>();
        String user = jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request));
        news.setMod_id(user);
        int in =newsService.updateNews(news);
        if(in == 1){
            resultModel.setSuccess(true);
            resultModel.setMessage("success update data");
        }else{
            resultModel.setSuccess(false);
            resultModel.setMessage("fail update data");
        }
        return resultModel;
    }

    @PostMapping("delete")
    public ResultModel<Object> deleteNews(@RequestBody Map<String, Integer> map){
        ResultModel<Object> result = new ResultModel();
        result.setData(Integer.valueOf(newsService.deleteNews(String.valueOf(map.get("news_idx")))));
        result.setSuccess(true);
        return result;
    }




}
