package com.zhang.springcase.datasource.dao;

import com.zhang.springcase.datasource.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<UserEntity> findAllUsers();
}
