package com.lixibing.majiangsp.majiang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class FileController {

    @PostMapping("/file/upload")
    @ResponseBody
    public Object upload(HttpServletRequest request){
        System.out.println(request);
        Map map = new HashMap();
        map.put("success",0);
        map.put("message","");
        map.put("url","/images/logos/editormd-logo-16x16.png");
        System.out.println(map);
        return map;
    }

}
