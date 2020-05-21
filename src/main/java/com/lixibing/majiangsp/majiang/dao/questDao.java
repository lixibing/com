package com.lixibing.majiangsp.majiang.dao;

import lombok.Data;

import java.util.Date;

@Data
public class questDao {
    private Integer id;
    private String title;
    private String des;
    private Date time_cr;
    private Date time_up;
    private Integer creator;
    private Integer countt;
    private Integer view_count;
    private Integer like_count;
    private String tag;
}
