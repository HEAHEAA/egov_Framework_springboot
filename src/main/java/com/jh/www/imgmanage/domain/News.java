package com.jh.www.imgmanage.domain;

import lombok.Data;

@Data
public class News {
    private int news_idx;
    private String news_content;

    private String user_id;
    private String mod_id;

    private int node_id;
}
