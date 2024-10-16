package com.jh.www.imgmanage.domain;

import lombok.Data;

@Data
public class FileList {

    private int idx;
    private String origin_fileName;
    private String upload_fileName;
    private String reg_date;
}
