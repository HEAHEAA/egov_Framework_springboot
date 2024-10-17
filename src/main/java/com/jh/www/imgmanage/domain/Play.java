package com.jh.www.imgmanage.domain;

import lombok.Data;

import java.util.List;

@Data
public class Play {
    private int pm_idx;

    private String pm_reg_date;

    private int pm_use;
    private List<Integer> file_idx;
    private List<Integer> time;
    private String file_list;
    private String running_time;
    private String pm_user_id;
    private int node_id;
}
