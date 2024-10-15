package com.jh.www.imgmanage.user.service;

import com.jh.www.imgmanage.domain.*;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService{
    String validRefreshToken(String user_id);
    int updateRefreshToken(Token token);
    User getUserForLogin(User user);
    User getUser(String id);
    void insertUser(User user);

    List<User> selectUserList(PageModel pageModel);
}
