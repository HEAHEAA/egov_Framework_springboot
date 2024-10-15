package com.jh.www.imgmanage.user.controller;

import com.jh.www.imgmanage.config.egovconfig.JwtTokenProvider;
import com.jh.www.imgmanage.domain.ResultModel;
import com.jh.www.imgmanage.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/*")
public class UserRestController {

    @Autowired
    UserService userService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @GetMapping("user")
    public ResultModel<Object> selectAllUser(){
        ResultModel<Object> resultModel = new ResultModel<>();
        resultModel.setData(userService.selectAllUser());
        resultModel.setMessage("userList success");
        resultModel.setSuccess(true);
        return resultModel;
    }

    @GetMapping("user/{id}")
    public ResultModel<Object> selectUserById(@PathVariable("id") String id){
        ResultModel<Object> resultModel = new ResultModel<>();
        resultModel.setData(userService.selectByIdUser(id));
        resultModel.setSuccess(true);
        resultModel.setMessage("user " + id + "번");
        return resultModel;
    }




}
