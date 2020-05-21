package com.lixibing.majiangsp.majiang.dao;

import lombok.Data;

import java.util.Date;


@Data
public class userDao {
   private Integer id;
    private String name;
    private String account_id;
    private String token;
 /*   private Long time_cr;
    private Long  time_up;*/
    private Date time_cr;
    private Date  time_up;
    private String url;
}
