package com.jh.www.imgmanage.play.controller;

import com.jh.www.imgmanage.domain.Play;
import com.jh.www.imgmanage.domain.ResultModel;
import com.jh.www.imgmanage.play.service.PlayService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/play/*")
public class PlayController {

    @Autowired
    private PlayService playService;

    @GetMapping("/list/{node_id}")
    public ResultModel<Object> playList(@PathVariable String node_id){
        ResultModel<Object> resultModel = new ResultModel<>();
        List<Play> play = playService.playList(Integer.parseInt(node_id));
        resultModel.setData(play);
        resultModel.setSuccess(true);
        resultModel.setMessage("재생목록 리스트");
        return resultModel;
    }

}
