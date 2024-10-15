package com.jh.www.imgmanage.user.service;

import com.jh.www.imgmanage.config.egovconfig.Encryption;
import com.jh.www.imgmanage.domain.*;

import com.jh.www.imgmanage.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Primary
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private Encryption encryption;

    @Override
    public String validRefreshToken(String user_id) {
        return userMapper.validRefreshToken(user_id);
    }

    @Override
    public int updateRefreshToken(Token token) {
        return userMapper.updateRefreshToken(token);
    }

    @Override
    public User getUserForLogin(User user) {
        String pw = user.getUser_pwd();
        String expw = encryption.encryptPassword(pw);
        String enpw =userMapper.getUserPw(user.getUser_id());
        boolean con =encryption.checkPassword(pw,enpw);
        if(con){
            user.setUser_pwd(expw);
            LogData logData = new LogData();
            logData.setUrl_type("api");
            logData.setUrl("/api/login");
            logData.setUser_id(user.getUser_id());
        }
        return userMapper.getUserForLogin(user);
    }

    @Override
    public User getUser(String id) {
        return userMapper.getUser(id);
    }

    @Override
    @Transactional
    public void insertUser(User user) {
        String pw = user.getUser_pwd();
        String enpw = encryption.encryptPassword(pw);
        user.setUser_pwd(enpw);
        userMapper.insertUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.getUser(username);
        if (!Objects.equals(username, user.getUser_id())){
            throw new UsernameNotFoundException("Not found user :"+username);
        }
        return user;
    }


    @Override
    public List<User> selectUserList(PageModel pageModel) {
        if(pageModel.getCurrentPage() != 0){
            int page = pageModel.getCurrentPage();
            int pageElement = pageModel.getCurrentElement();
            if(page == 1){
                pageModel.setStart(0);
                pageModel.setEnd(pageElement);
            }else{
                pageModel.setStart(page*pageElement+1);
                pageModel.setEnd(page*pageElement+pageElement);
            }
            return userMapper.selectUserList(pageModel);
        }else{
            return userMapper.selectUserList(pageModel);
        }

    }

}

