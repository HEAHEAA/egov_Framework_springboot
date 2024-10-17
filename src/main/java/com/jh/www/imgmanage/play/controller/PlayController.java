package com.jh.www.imgmanage.play.controller;

import com.jh.www.imgmanage.config.egovconfig.JwtTokenProvider;
import com.jh.www.imgmanage.domain.*;
import com.jh.www.imgmanage.play.service.PlayService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/play/*")
public class PlayController {

    @Autowired
    private PlayService playService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @GetMapping("/list/{node_id}")
    public ResultModel<Object> playList(@PathVariable String node_id) {
        ResultModel<Object> resultModel = new ResultModel<>();
        List<Play> play = playService.playList(Integer.parseInt(node_id));
        resultModel.setData(play);
        resultModel.setSuccess(true);
        resultModel.setMessage("재생목록 리스트");
        return resultModel;
    }

    @PostMapping("/insert")
    public ResultModel<Object> insertPlay(HttpServletRequest request, @RequestBody Play play) {
        ResultModel<Object> resultModel = new ResultModel<>();
        String user = jwtTokenProvider.getUserPk(jwtTokenProvider.resolveToken(request));
        play.setPm_user_id(user);
        int in = playService.playInsert(play);
        if (in == 1) {
            resultModel.setSuccess(true);
            resultModel.setMessage("success insert data");
        } else {
            resultModel.setSuccess(false);
            resultModel.setMessage("fail to insert data");
        }
        return resultModel;
    }



}
