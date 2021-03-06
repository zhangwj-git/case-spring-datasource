package com.zhang.springcase.datasource.api;

import com.zhang.springcase.datasource.common.ResponseCode;
import com.zhang.springcase.datasource.common.ResponseEntity;
import com.zhang.springcase.datasource.entity.UserEntity;
import com.zhang.springcase.datasource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user/")
public class UserApi {

    @Autowired
    private UserService userService;

    @GetMapping("list")
    public ResponseEntity<?> getUsers(){
        List<UserEntity> userList = new ArrayList<>();
        userList.addAll(userService.findAll());
        userList.addAll(userService.listUser());
        userList.addAll(userService.findAll());
        userList.addAll(userService.listUser());
        userList.addAll(userService.findAll());
        userList.addAll(userService.listUser());
        return new ResponseEntity.Builder().response(ResponseCode.SUCCESS).data(userList).build();
    }
}
