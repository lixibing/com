package com.lixibing.majiangsp.majiang.controller;


import com.lixibing.majiangsp.majiang.dao.userDao;
import com.lixibing.majiangsp.majiang.mapper.questMapper;
import com.lixibing.majiangsp.majiang.mapper.userMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class wtwtcontroller {


    @Autowired
    private questMapper questMapper;

    @Autowired
    private userMapper userMapper;

    @GetMapping("/wdwt/{action}")
    public String wdwt(@PathVariable(name = "action")String action,
                       Model model,
                       @RequestParam(value = "page",defaultValue = "1") Integer page
                        , @RequestParam(value = "size",defaultValue = "5") Integer size,
                       HttpServletRequest request){
        Integer pagetop = size*(page-1);
        System.out.println(action);
        userDao userDao =(userDao) request.getSession().getAttribute("user");
        if (userDao == null){
            return "redirect:/";
        }
        System.out.println(userDao);
        if ("wt".equals(action)){
            model.addAttribute("s","wdwt");
            model.addAttribute("sname","我的提问");
            Integer id =userDao.getId();
            List<Map> jhDaos =  questMapper.listto(id,pagetop,size);
            model.addAttribute("jhDaos",jhDaos);
        }else if ("zxhf".equals(action)){
            model.addAttribute("s","zxhf");
            model.addAttribute("sname","最新回复");
        }

        return "/wdwt";

    }
}
