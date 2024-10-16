package com.jh.www.imgmanage.user.controller;

import com.jh.www.imgmanage.config.egovconfig.JwtTokenProvider;
import com.jh.www.imgmanage.domain.ResultModel;
import com.jh.www.imgmanage.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/*")
public class UserRestController {

    @Autowired
    UserService userService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @GetMapping("user")
    public ResultModel<Object> selectAllUser(HttpServletRequest request){
        String access_token = jwtTokenProvider.resolveToken(request);
        ResultModel<Object> resultModel = new ResultModel<>();
        int auth = jwtTokenProvider.getUserRole(access_token);

        if(auth != 1) {
            resultModel.setMessage("권한이 없습니다.");
            resultModel.setSuccess(false);
            return resultModel;
        }
        resultModel.setData(userService.selectAllUser());
        resultModel.setMessage("userList success");
        resultModel.setSuccess(true);
        return resultModel;
    }

    @GetMapping("user/{id}")
    public ResultModel<Object> selectUserById(HttpServletRequest request,
                                              @PathVariable("id") String id){
        String access_token = jwtTokenProvider.resolveToken(request);
        ResultModel<Object> resultModel = new ResultModel<>();
        int auth = jwtTokenProvider.getUserRole(access_token);
        if(auth != 1) {
            resultModel.setMessage("권한이 없습니다.");
            resultModel.setSuccess(true);
            return resultModel;
        }
        resultModel.setData(userService.selectByIdUser(id));
        resultModel.setSuccess(true);
        resultModel.setMessage("user " + id + "번");
        return resultModel;
    }





}
