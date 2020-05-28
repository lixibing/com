package com.lixibing.majiangsp.majiang.ljq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
//@EnableWebMvc
public class Webconfig implements WebMvcConfigurer {

    @Autowired
    private Seestion seestion;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(seestion).addPathPatterns("/**");
    }


}
