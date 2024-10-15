package com.jh.www.imgmanage.domain;

import lombok.Data;

@Data
public class Token {
    private String user_id;
    private String token_type;
    private String refresh_token;
    private String access_token;
    private int roles;
    private int expires_in;
}
