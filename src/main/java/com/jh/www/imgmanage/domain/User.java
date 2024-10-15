package com.jh.www.imgmanage.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

@Data
public class User implements UserDetails {
    private int dt_op_user_id;
    private int sys_op_user_class_id;
    private String user_name;
    private String user_id;
    private String user_pwd;
    private String user_part;
    private String user_grade;
    private String user_phone;
    private String user_email;
    private String user_image;
    private String user_remark;
    private Date user_reg_date;
    private int user_status;
    private String user_token;
    private Token auth_token;
    private String user_class_name;
    private String total_row;

    private Collection<? extends GrantedAuthority> authorities;


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public String getPassword() {
        return this.user_pwd;
    }
    @Override
    public String getUsername() {
        return this.user_name;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
