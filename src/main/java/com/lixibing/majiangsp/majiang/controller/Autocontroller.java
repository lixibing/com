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

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
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
                            , HttpServletRequest request){
        githubdao gitto = new githubdao();
        gitto.setClient_id(clientid);
        gitto.setClient_secret(clientsecret);
        gitto.setCode(code);
        gitto.setRedirect_uri(redirecturi);
        gitto.setState(state);
        String ustoken = githubPro.gettock(gitto);
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
            userDao.setToken(UUID.randomUUID().toString());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println(df.format(new java.util.Date()));
            /*userDao.setTime_cr(System.currentTimeMillis());
            userDao.setTime_up(System.currentTimeMillis());*/
            userDao.setTime_cr(new java.util.Date());
            userDao.setTime_up(new java.util.Date());
            System.out.println(System.currentTimeMillis());
            userMapper.insert(userDao);
            request.getSession().setAttribute("user",user);
            return "redirect:/";
        }else {
            return "redirect:/";
        }

    }
}
