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

import java.util.*;

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
    public List<Map<String, Object>> selectAllUser() {
        List<User> users = userMapper.selectAllUser();
        return filterUsers(users);
    }

    @Override
    public List<Map<String, Object>> selectByIdUser(String id) {
        List<User> users = userMapper.selectByIdUser(id);
        return filterUsers(users);
    }

    // 공통 로직 처리하는 메서드
    private List<Map<String, Object>> filterUsers(List<User> users) {
        List<Map<String, Object>> filteredUsers = new ArrayList<>();

        for (User user : users) {
            Map<String, Object> filteredUser = new HashMap<>();
            filteredUser.put("dt_op_user_id", user.getDt_op_user_id());
            filteredUser.put("sys_op_user_class_id", user.getSys_op_user_class_id());
            filteredUser.put("user_name", user.getUser_name());
            filteredUser.put("user_id", user.getUser_id());
            filteredUser.put("user_status", user.getUser_status());
            filteredUsers.add(filteredUser);
        }

        return filteredUsers;
    }




}

