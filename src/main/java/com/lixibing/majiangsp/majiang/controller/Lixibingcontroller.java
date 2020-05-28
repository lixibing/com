package com.lixibing.majiangsp.majiang.controller;

import com.lixibing.majiangsp.majiang.dao.jhDao;
import com.lixibing.majiangsp.majiang.dao.questDao;
import com.lixibing.majiangsp.majiang.dao.userDao;
import com.lixibing.majiangsp.majiang.mapper.questMapper;
import com.lixibing.majiangsp.majiang.mapper.userMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class Lixibingcontroller {

    @Autowired
    private userMapper userMapper;

    @Autowired
    private questMapper questMapper;


    @RequestMapping("/")
    public String lixibing(HttpServletRequest request,Model model,
                           @RequestParam(value = "page",defaultValue = "1") Integer page
                            ,@RequestParam(value = "size",defaultValue = "5") Integer size){
        Integer pagetop = size*(page-1);
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
        userDao userDao =(userDao) request.getSession().getAttribute("user");
        System.out.println(userDao);
        List<Map> jhDaos =  questMapper.list(pagetop,size);
        System.out.println("999");
        System.out.println(jhDaos.toString());
        model.addAttribute("jhDaos",jhDaos);
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
                            @RequestParam(value = "id",required = false) Integer id,
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


        userDao userDao =(userDao) request.getSession().getAttribute("user");
        System.out.println(userDao);
        if (userDao == null){
            model.addAttribute("mode","请重新登录");
            return "faqi";
        }
        System.out.println(title);
        questDao questDao = new questDao();
        questDao.setTag(tag);
        questDao.setTitle(title);
        questDao.setDes(des);
        questDao.setTime_cr(new java.util.Date());
        questDao.setTime_up(new java.util.Date());
        questDao.setCreator(userDao.getId());
        if (id != null){
            questDao.setId(id);
            questMapper.update(questDao);
        }else{
            questMapper.insert(questDao);
        }

        return "redirect:/";
    }
    @GetMapping("/faqi/{id}")
    public String xiugai(@PathVariable(name = "id") Integer id,
                            HttpServletRequest request,
                            Model model){
        List<Map> list=questMapper.getById(id);
        model.addAttribute("title",list.get(0).get("title"));
        model.addAttribute("des",list.get(0).get("des"));
        model.addAttribute("tag",list.get(0).get("tag"));
        model.addAttribute("tag",list.get(0).get("id"));
        return "faqi";
    }
}
