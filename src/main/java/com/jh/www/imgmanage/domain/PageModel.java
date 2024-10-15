package com.jh.www.imgmanage.domain;

import lombok.Data;

import java.util.List;

@Data
public class PageModel {
    private int start;
    private int end;
    private int totalCount;
    private int currentPage;
    private int totalPage;
    private int currentElement;
    private int selTime;
    private String sort;
    private String ord;
    private String date;
    private String cate;
    private String subcate;
    private String sdate;
    private String edate;
    private String type;

    private String stype;
    private String svalue;
    private List<String> node_id;
    private List<String> sensor_id;
    private List<Column> d;

    public PageModel() {
        this.start = -1;
        this.end = -1;
    }
}
