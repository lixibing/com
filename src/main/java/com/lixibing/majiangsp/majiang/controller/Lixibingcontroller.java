package com.lixibing.majiangsp.majiang.controller;

import com.lixibing.majiangsp.majiang.dao.questDao;
import com.lixibing.majiangsp.majiang.dao.userDao;
import com.lixibing.majiangsp.majiang.mapper.questMapper;
import com.lixibing.majiangsp.majiang.mapper.userMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class Lixibingcontroller {

    @Autowired
    private userMapper userMapper;

    @Autowired
    private questMapper questMapper;

    @RequestMapping("/")
    public String lixibing(HttpServletRequest request){
        Cookie[] cookies= request.getCookies();
        if (cookies !=null){
            for (Cookie c :cookies){
                if (c.getName().equals("token")){
                    String token = c.getValue();
                    userDao userDao =userMapper.select(token);
                    if (userDao != null){
                        request.getSession().setAttribute("user",userDao);
                    }
                    break;
                }


            }
        }


        return "lixibing";
    }
    @GetMapping("/faqi")
    public String lixibing1(){
        System.out.println("111");
        return "faqi";
    }

    @PostMapping("/faqi")
    public String lixibing2(@RequestParam(value = "title",required = false) String title,
                            @RequestParam(value = "des",required = false) String des,
                            @RequestParam(value = "tag",required = false) String tag,
                            HttpServletRequest request,
                            Model model){
        System.out.println("666");
        model.addAttribute("title",title);
        model.addAttribute("des",des);
        model.addAttribute("tag",tag);
        if (title =="" || title == null){
            model.addAttribute("mode","标题不能为空");
            return "/faqi";
        }
        if (des =="" || des == null){
            model.addAttribute("mode","问题不能为空");
            return "/faqi";
        }
        if (tag =="" || tag == null){
            model.addAttribute("mode","标签不能诶空");
            return "/faqi";
        }

        userDao userDao = null ;
        Cookie[] cookies= request.getCookies();
        for (Cookie c :cookies){
            if (c.getName().equals("token")){
                String token = c.getValue();
                 userDao =userMapper.select(token);
                if (userDao != null){
                    request.getSession().setAttribute("user",userDao);
                }
                break;
            }
        }
        if (userDao == null){
            model.addAttribute("mode","请重新登录");
            return "faqi";
        }
        System.out.println(title);
        questDao questDao = new questDao();
        questDao.setTag(title);
        questDao.setTitle(tag);
        questDao.setDes(des);
        questDao.setTime_cr(new java.util.Date());
        questDao.setTime_up(new java.util.Date());
        questDao.setCreator(userDao.getId());
        questMapper.insert(questDao);
        return "redirect:/";
    }
}
