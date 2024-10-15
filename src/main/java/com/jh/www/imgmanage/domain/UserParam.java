package com.jh.www.imgmanage.domain;

import lombok.Data;

@Data
public class UserParam {
    private String user_id;
    private String user_name;
    private int user_role;
    private Token auth_token;
}
