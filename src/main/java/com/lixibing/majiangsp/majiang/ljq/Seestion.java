package com.lixibing.majiangsp.majiang.ljq;

import com.lixibing.majiangsp.majiang.dao.userDao;
import com.lixibing.majiangsp.majiang.mapper.userMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class Seestion implements HandlerInterceptor {

    @Autowired
    private userMapper userMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
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
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
