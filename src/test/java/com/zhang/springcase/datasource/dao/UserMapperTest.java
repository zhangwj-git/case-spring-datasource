package com.zhang.springcase.datasource.dao;

import com.zhang.springcase.datasource.BaseJunitTest;
import com.zhang.springcase.datasource.entity.UserEntity;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserMapperTest extends BaseJunitTest{

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testFindAllUsers(){
        List<UserEntity> users = userMapper.findAllUsers();
        Assert.assertFalse(users.isEmpty());
    }
}
