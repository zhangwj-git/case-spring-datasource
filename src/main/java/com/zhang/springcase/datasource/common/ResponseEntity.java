package com.zhang.springcase.datasource.common;


import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * 通用返回类
 * @param <T>
 */
@Getter
public class ResponseEntity<T> {

    private final String msgId = UUID.randomUUID().toString().replaceAll("-","");

    private Integer code;//错误码

    private String msg;//错误信息

    private T data;//响应内容

    private final Long timestamp = Long.parseLong(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHMMssSSS")));

    private ResponseEntity(){

    }

    private ResponseEntity(Integer code, String msg, T data){
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public static class Builder<T>{

        private Integer code;

        private String msg;

        private T data;

        public Builder<?> code(Integer code){
            this.code = code;
            return this;
        }

        public Builder<?> msg(String msg){
            this.msg = msg;
            return this;
        }

        public Builder<?> data(T data){
            this.data = data;
            return this;
        }

        public Builder<?> response(ResponseCode responseCode){
            this.code = responseCode.getCode();
            this.msg = responseCode.getMsg();
            return this;
        }

        public ResponseEntity<?> build(){
            return new ResponseEntity(this.code,this.msg,this.data);
        }
    }
}
