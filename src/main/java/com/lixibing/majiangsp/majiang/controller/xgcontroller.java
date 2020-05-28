package com.lixibing.majiangsp.majiang.controller;

import com.lixibing.majiangsp.majiang.dao.questDao;
import com.lixibing.majiangsp.majiang.mapper.commentMapper;
import com.lixibing.majiangsp.majiang.mapper.questMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Controller
public class xgcontroller {

    @Autowired
    private questMapper questMapper;

    @Autowired
    private commentMapper commentMapper;

    @GetMapping("/xg/{id}")
    public String xg(@PathVariable(name = "id")Integer id,
                     Model model){
        List<Map> questDao =questMapper.getById(id);
        int countt =(int) questDao.get(0).get("countt");
        ++countt;
        questMapper.inyds(id);
        System.out.println(questDao.get(0));
        List<Map> list =commentMapper.getById1(id);
        model.addAttribute("jhDaos",questDao.get(0));
        model.addAttribute("list",list);
        String tag=(String) questDao.get(0).get("tag");
        List<Map> glwt = questMapper.getBq(tag,id);
        model.addAttribute("glwt",glwt);
        System.out.println(glwt);
        return "xg";
    }
}
