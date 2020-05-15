package com.lixibing.majiangsp.majiang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Lixibingcontroller {

    @RequestMapping("/")
    public String lixibing(){
        return "lixibing";
    }
}
