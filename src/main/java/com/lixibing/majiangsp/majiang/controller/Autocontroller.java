package com.lixibing.majiangsp.majiang.controller;


import com.lixibing.majiangsp.majiang.dao.githubdao;
import com.lixibing.majiangsp.majiang.dao.githubuser;
import com.lixibing.majiangsp.majiang.provider.GithubPro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/callback")
    public String callbock(@RequestParam(name = "code") String code
                            ,@RequestParam(name = "state") String state){
        githubdao gitto = new githubdao();
        gitto.setClient_id(clientid);
        gitto.setClient_secret(clientsecret);
        gitto.setCode(code);
        gitto.setRedirect_uri(redirecturi);
        gitto.setState(state);
        String ustoken = githubPro.gettock(gitto);
        githubuser user =githubPro.getuser(ustoken);
        githubuser s =new githubuser();
        s.setName("lalla");;
        user =s;
        System.out.println(user.getName());
        /*System.out.println(user.getName());*/
        return "lixibing";
    }
}
