package com.zhang.springcase.datasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 应用启动类,springboot应用入口
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringCaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCaseApplication.class, args);
	}
	
}
