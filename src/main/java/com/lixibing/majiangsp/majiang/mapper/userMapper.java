package com.lixibing.majiangsp.majiang.mapper;

import com.lixibing.majiangsp.majiang.dao.userDao;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface userMapper {


    @Insert("insert into user(name,account_id,token,time_cr,time_up) " +
            "values " +
            "( #{name},#{account_id},#{token},#{time_cr},#{time_up} )" )
     void insert(userDao userDao);
}
