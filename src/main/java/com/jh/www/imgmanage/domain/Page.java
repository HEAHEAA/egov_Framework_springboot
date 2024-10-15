package com.jh.www.imgmanage.domain;

import lombok.Data;

@Data
public class Page {
    private int page_id;
    private String page_name;
    private String page_link;
    private int page_use;
}
