package com.lixibing.majiangsp.majiang.dao;

import lombok.Data;

import java.util.Date;

@Data
public class commentDao {
    private int id;
    private int puest_id;
    private int type;
    private int user_id;
    private Date time_cr;
    private Date time_up;
    private int dzs;
    private String content;

}
