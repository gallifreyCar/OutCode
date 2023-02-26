package com.gallifrey.outcode.dao;

import com.gallifrey.outcode.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);


    //@Param注解用于给参数名取别名
    //如果只有一个参数，并且在<if>中使用，必须起别名
    int selectDiscussPostRows(@Param("userId") int userId);
}
