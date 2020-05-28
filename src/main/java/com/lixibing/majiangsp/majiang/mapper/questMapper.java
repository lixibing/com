package com.lixibing.majiangsp.majiang.mapper;

import com.lixibing.majiangsp.majiang.dao.jhDao;
import com.lixibing.majiangsp.majiang.dao.questDao;
import com.lixibing.majiangsp.majiang.dao.userDao;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface questMapper {


    @Insert("insert into quest(title,tag,des,time_cr,time_up,creator) " +
            "values " +
            "( #{title},#{tag},#{des},#{time_cr},#{time_up},#{creator} )" )
    void insert(questDao questDao);
    @Update("update quest set title=#{title},tag=#{tag},des=#{des} where id =#{id}" )
    void update(questDao questDao);


    @Select("select * from user where token =#{token}")
    userDao select(@Param("token") String token);


    @Select("select  q.id, q.title ,q.des,q.time_cr,q.time_up,q.creator,q.countt,q.view_count,q.like_count,q.tag,u.url from quest q inner join user u on q.creator = u.id limit #{pagetop},#{size}")
    List<Map> list(@Param(value = "pagetop") Integer pagetop, @Param(value = "size")Integer size);
    @Select("select  q.id, q.title ,q.des,q.time_cr,q.time_up,q.creator,q.countt,q.view_count,q.like_count,q.tag,u.url from quest q inner join user u on q.creator = u.id where creator=#{id} limit #{pagetop},#{size}")
    List<Map> listto(@Param(value = "id")Integer id,@Param(value = "pagetop") Integer pagetop,@Param(value = "size") Integer size);


    @Select("select  q.id, q.title ,q.des,q.time_cr,q.time_up,q.creator,q.countt,q.view_count,q.like_count,q.tag,u.url url,u.name name from quest q inner join user u on q.creator = u.id where q.id =#{id}")
    List<Map> getById(@Param(value = "id") Integer id);

    @Update("update quest set countt= COUNTT +1 where id =#{id}" )
    void inyds(@Param(value = "id")Integer id);

    @Select("select  id, title ,tag from quest  where tag regexp #{tag} and id!=#{id}")
    List<Map> getBq(@Param(value = "tag") String tag,@Param(value = "id")Integer id);
}
