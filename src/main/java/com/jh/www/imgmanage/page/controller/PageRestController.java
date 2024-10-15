package com.jh.www.imgmanage.page.controller;

import com.jh.www.imgmanage.config.egovconfig.JwtTokenProvider;
import com.jh.www.imgmanage.domain.ResultModel;
import com.jh.www.imgmanage.page.service.PageService;
import com.jh.www.imgmanage.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/*")
public class PageRestController {
    @Autowired
    PageService pageService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @PostMapping(value = "menu")
    public ResultModel<Object> getMenu(HttpServletRequest request){
        ResultModel<Object> result = new ResultModel<>();
        String Access_token = jwtTokenProvider.resolveToken(request);
        if(jwtTokenProvider.validateToken(Access_token)){
            result.setData(pageService.getMenu());
            result.setSuccess(true);
            result.setMessage("get Menu");
        }else{
            result.setSuccess(false);
            result.setMessage("token expired");
        }
        return result;
    }
}
