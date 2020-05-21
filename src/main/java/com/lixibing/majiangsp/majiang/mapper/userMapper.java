package com.lixibing.majiangsp.majiang.mapper;

import com.lixibing.majiangsp.majiang.dao.userDao;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface userMapper {


    @Insert("insert into user(name,account_id,token,time_cr,time_up,url) " +
            "values " +
            "( #{name},#{account_id},#{token},#{time_cr},#{time_up},#{url} )" )
     void insert(userDao userDao);

    @Select("select * from user where token =#{token}")
    userDao select(@Param("token") String token);
}
