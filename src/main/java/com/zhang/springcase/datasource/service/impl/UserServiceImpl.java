package com.zhang.springcase.datasource.service.impl;

import com.zhang.springcase.datasource.dao.UserMapper;
import com.zhang.springcase.datasource.entity.UserEntity;
import com.zhang.springcase.datasource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;


    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> findAll() {
        return userMapper.findAllUsers();
    }

    @Override
    @Transactional
    public List<UserEntity> listUser() {
        return userMapper.findAllUsers();
    }
}
