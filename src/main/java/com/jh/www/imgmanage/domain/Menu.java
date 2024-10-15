package com.jh.www.imgmanage.domain;

import lombok.Data;

import java.util.List;

@Data
public class Menu {
    private String id;
    private String ptId;
    private String type;
    private String text;
    private String url;
    private String icon;
    private List<Menu> children;
}
