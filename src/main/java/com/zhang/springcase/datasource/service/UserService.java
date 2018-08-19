package com.zhang.springcase.datasource.service;

import com.zhang.springcase.datasource.entity.UserEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {


    List<UserEntity> findAll();

    List<UserEntity> listUser();
}
