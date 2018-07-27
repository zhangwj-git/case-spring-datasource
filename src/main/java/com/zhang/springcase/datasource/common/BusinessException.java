package com.zhang.springcase.datasource.common;

public class BusinessException extends RuntimeException{

    private Integer code;

    private String msg;

    public BusinessException(ResponseCode response){
        super("code:["+response.getCode()+"],msg:["+response.getMsg()+"]");
        this.code = response.getCode();
        this.msg = response.getMsg();
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
