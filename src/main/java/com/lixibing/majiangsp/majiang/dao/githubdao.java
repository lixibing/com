package com.lixibing.majiangsp.majiang.dao;

import lombok.Data;

@Data
public class githubdao {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}
