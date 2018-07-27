package com.zhang.springcase.datasource.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class UserEntity implements Serializable{

    private String userId;

    private String userName;

    private String password;

    private String email;

    private String phone;
}
