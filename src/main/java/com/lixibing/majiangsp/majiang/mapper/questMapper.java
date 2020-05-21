package com.lixibing.majiangsp.majiang.mapper;

import com.lixibing.majiangsp.majiang.dao.questDao;
import com.lixibing.majiangsp.majiang.dao.userDao;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface questMapper {


    @Insert("insert into quest(title,tag,des,time_cr,time_up,creator) " +
            "values " +
            "( #{title},#{tag},#{des},#{time_cr},#{time_up},#{creator} )" )
     void insert(questDao questDao);

    @Select("select * from user where token =#{token}")
    userDao select(@Param("token") String token);
}
