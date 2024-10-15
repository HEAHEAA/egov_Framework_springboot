package com.jh.www.imgmanage.user.mapper;

import com.jh.www.imgmanage.domain.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User getUserForLogin(User user);
    String validRefreshToken(String user_id);
    int updateRefreshToken(Token token);

    User getUser(String id);
    String getUserPw(String id);
    void insertUser(User user);
}
