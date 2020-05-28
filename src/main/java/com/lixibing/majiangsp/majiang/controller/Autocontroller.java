package com.lixibing.majiangsp.majiang.controller;


import com.lixibing.majiangsp.majiang.dao.githubdao;
import com.lixibing.majiangsp.majiang.dao.githubuser;
import com.lixibing.majiangsp.majiang.dao.userDao;
import com.lixibing.majiangsp.majiang.mapper.userMapper;
import com.lixibing.majiangsp.majiang.provider.GithubPro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.UUID;

@Controller
public class Autocontroller {

    @Autowired
    private GithubPro githubPro;

    @Value("${github.client.id}")
    private String clientid;
    @Value("${github.client.secret}")
    private String clientsecret;
    @Value("${github.redirect.uri}")
    private String redirecturi;

    @Autowired
    private userMapper userMapper;

    @GetMapping("/callback")
    public String callbock(@RequestParam(name = "code") String code
            , @RequestParam(name = "state") String state
            , HttpServletRequest request
            , HttpServletResponse response){
        githubdao gitto = new githubdao();
        gitto.setClient_id(clientid);
        gitto.setClient_secret(clientsecret);
        gitto.setCode(code);
        gitto.setRedirect_uri(redirecturi);
        gitto.setState(state);
        String ustoken = githubPro.gettock(gitto);
        System.out.println(ustoken);
        githubuser user =githubPro.getuser(ustoken);
        System.out.println(user);
        githubuser s =new githubuser();
        s.setName("厉锡兵");
        user =s;
        System.out.println(user.getName());
        /*System.out.println(user.getName());*/
        if (user != null){
            userDao userDao =new userDao();
            userDao.setName(s.getName());
            userDao.setAccount_id(ustoken);
            String token =UUID.randomUUID().toString();
            userDao.setToken(token);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println(df.format(new java.util.Date()));
            /*userDao.setTime_cr(System.currentTimeMillis());
            userDao.setTime_up(System.currentTimeMillis());*/
            userDao.setTime_cr(new java.util.Date());
            userDao.setTime_up(new java.util.Date());
            System.out.println(System.currentTimeMillis());
            userDao.setUrl("https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1151834211,158545213&fm=26&gp=0.jpg");

            String name =s.getName();
            String st = userMapper.findtoke(name);


            System.out.println("st"+st);
            if (st == null){
                userMapper.insert(userDao);
            }else{

            }
            userDao userDao1 = userMapper.selectto(name);
            response.addCookie(new Cookie("token",token));
            request.getSession().setAttribute("user",userDao1);
            return "redirect:/";
        }else {
            return "redirect:/";
        }

    }
    @GetMapping("/out")
    public String out(
             HttpServletRequest request
            , HttpServletResponse response){
        System.out.println("88");
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
