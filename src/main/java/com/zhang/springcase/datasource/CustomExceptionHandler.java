package com.zhang.springcase.datasource;

import com.zhang.springcase.datasource.common.BusinessException;
import com.zhang.springcase.datasource.common.ResponseCode;
import com.zhang.springcase.datasource.common.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 自定义统一异常处理类
 */
@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    /**
     * 统一异常处理
     * @param e 异常
     * @return 返回
     */
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<?> exceptionHandler(Exception e){
		log.error("Exception:",e);
		if (e instanceof BusinessException){
            BusinessException be = (BusinessException) e;
            return new ResponseEntity.Builder().code(be.getCode()).msg(be.getMsg()).build();
        }
        return new ResponseEntity.Builder().response(ResponseCode.UNKNOW_ERROR).build();
	}
}
