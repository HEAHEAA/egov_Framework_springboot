package com.jh.www.imgmanage.domain;

import lombok.Data;

@Data
public class ResultModel<T> {
    private T data;

    private int totalCount;
    private boolean success = false;
    private String message;
}
