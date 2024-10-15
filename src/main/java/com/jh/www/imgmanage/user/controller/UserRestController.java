package com.jh.www.imgmanage.user.controller;

import com.jh.www.imgmanage.config.egovconfig.JwtTokenProvider;
import com.jh.www.imgmanage.domain.PageModel;
import com.jh.www.imgmanage.domain.ResultModel;
import com.jh.www.imgmanage.domain.User;
import com.jh.www.imgmanage.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/*")
public class UserRestController {

    @Autowired
    UserService userService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @GetMapping("selectUserList")
    public ResultModel<Object> selectUserList(HttpServletRequest request,
                                              @RequestParam(required = false, defaultValue = "0") String current_page,
                                              @RequestParam(required = false, defaultValue = "0") String current_element) {
        ResultModel<Object> result = new ResultModel<>();
        PageModel pageModel = new PageModel();
        pageModel.setCurrentElement(Integer.parseInt(current_element) == 0 ? 0 : Integer.parseInt(current_element));
        pageModel.setCurrentPage(Integer.parseInt(current_page) == 0 ? 1 : Integer.parseInt(current_page));

        List<User> userList = userService.selectUserList(pageModel);
        result.setData(userList);
        result.setSuccess(true);
        return result;
    }



}
