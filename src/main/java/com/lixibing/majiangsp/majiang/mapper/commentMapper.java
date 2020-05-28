package com.lixibing.majiangsp.majiang.mapper;

import com.lixibing.majiangsp.majiang.dao.commentDao;
import org.apache.ibatis.annotations.*;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface commentMapper {


    @Insert("insert into comment(puest_id,type,user_id,time_cr,time_up,content) " +
            "values " +
            "( #{puest_id},#{type},#{user_id},#{time_cr},#{time_up},#{content} )" )
     int insert(commentDao commentDao);

    @Update("update quest set view_count= view_count +1 where id =#{id}" )
    void infor(@Param(value = "id")Integer id);

    @Select("select  * from" +
            " quest q inner join comment c on q.id = c.puest_id " +
            " inner join user u on u.id = q.creator  where q.id =#{id} order by c.time_cr")
    List<Map> getById1(@Param(value = "id") Integer id);
}
