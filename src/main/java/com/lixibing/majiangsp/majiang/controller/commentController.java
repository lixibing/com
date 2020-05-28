package com.lixibing.majiangsp.majiang.controller;

import com.lixibing.majiangsp.majiang.dao.commentDao;
import com.lixibing.majiangsp.majiang.dao.userDao;
import com.lixibing.majiangsp.majiang.mapper.commentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class commentController {


    @Autowired
    private commentMapper commentMapper;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody commentDao commentDao,
                       HttpServletRequest request){
        System.out.println("commentController");
        userDao userDao =(userDao) request.getSession().getAttribute("user");
        Map<Object,Object> objectObjectMap =new HashMap<>();

        System.out.println(userDao);
        if (userDao == null){
            objectObjectMap.put("code","2003");
            objectObjectMap.put("message","请重新登录");
            return objectObjectMap;
        }
        commentDao.setTime_cr(new java.util.Date());
        commentDao.setTime_up(new java.util.Date());
        commentDao.setUser_id(userDao.getId());
        System.out.println(commentDao);
        int i = commentMapper.insert(commentDao);
        int id =commentDao.getPuest_id();
        commentMapper.infor(id);

        if (i != 0){
            objectObjectMap.put("code","0000");
            objectObjectMap.put("message","成功");
        }else{
            objectObjectMap.put("code","1111");
            objectObjectMap.put("message","失败");
        }

        return objectObjectMap;

    }

}
