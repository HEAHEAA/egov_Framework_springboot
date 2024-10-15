package com.jh.www.imgmanage.view.controller;

import com.jh.www.imgmanage.config.egovconfig.JwtTokenProvider;
import com.jh.www.imgmanage.domain.*;
import com.jh.www.imgmanage.page.service.PageService;
import com.jh.www.imgmanage.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/*")
public class ViewRestController {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private PageService pageService;
    @Autowired
    private UserService userService;


    @PostMapping(value = "login")
    public ResultModel<Object> login(@RequestBody User user) {
        ResultModel<Object> result = new ResultModel<>();
        new User();
        User login;

        if(userService.getUserForLogin(user)!=null) {
            login = userService.getUserForLogin(user);
            User setData = userService.getUserForLogin(user);
            UserParam up = new UserParam();
            up.setUser_id(setData.getUser_id());
            up.setUser_name(setData.getUser_name());
            up.setUser_role(setData.getSys_op_user_class_id());
            up.setAuth_token(jwtTokenProvider.createAllToken(login.getUser_id()));
            result.setSuccess(true);
            result.setData(up);
            result.setMessage("신규 토큰 발행");
        } else {
            result.setSuccess(false);
            result.setMessage("아이디와 비밀번호를 제대로 입력 하였는지 확인해 주세요");
        }
        return result;
    }

    @PostMapping(value = "signUp")
    public ResultModel<Object> signUp(@RequestBody User user) {
        ResultModel<Object> result = new ResultModel<>();
        userService.insertUser(user);
        result.setSuccess(true);
        result.setMessage("회원 가입 성공");
        return result;
    }

    @GetMapping(value = "refresh")
    public ResultModel<Object> refreshToken(HttpServletRequest request){
        ResultModel<Object> result = new ResultModel<>();
        String refresh_token = jwtTokenProvider.resolveRefreshToken(request);
        if(jwtTokenProvider.validateRefreshToken(refresh_token)){
            result.setData(jwtTokenProvider.refreshAccessToken(refresh_token));
            result.setSuccess(true);
            result.setMessage("success issuance token");
        }else{
            result.setSuccess(false);
            result.setMessage("token expired");
        }
        return result;
    }

    @PostMapping(value = "logout")
    public ResultModel<Object> logoutUser(HttpServletRequest request){
        ResultModel<Object> result = new ResultModel<>();
        String Access_token = jwtTokenProvider.resolveToken(request);
        if(jwtTokenProvider.validateToken(Access_token)){
            Token token = new Token();
            token.setUser_id(jwtTokenProvider.getUserPk(Access_token));
            token.setRefresh_token("log out");
            userService.updateRefreshToken(token);
            result.setData("expired refresh_token");
            result.setSuccess(true);
            result.setMessage("user logout");
        }else{
            result.setSuccess(false);
            result.setMessage("token expired");
        }
        return result;
    }

}
