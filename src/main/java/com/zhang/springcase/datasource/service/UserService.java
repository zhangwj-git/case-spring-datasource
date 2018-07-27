package com.zhang.springcase.datasource.service;

import com.zhang.springcase.datasource.entity.UserEntity;

import java.util.List;

public interface UserService {

    List<UserEntity> findAll();
}
