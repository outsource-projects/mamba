package com.renguangli.mamba.mapper;

import org.apache.ibatis.annotations.Select;

import com.renguangli.mamba.entity.User;

public interface UserMapper {

    @Select("select user_id userId,username,password from user u where u.username=#{username}")
    User login(String username);
}
