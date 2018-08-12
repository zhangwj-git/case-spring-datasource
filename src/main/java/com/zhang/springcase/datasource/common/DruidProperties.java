package com.zhang.springcase.datasource.common;

import com.alibaba.druid.pool.DruidDataSource;
import com.zhang.springcase.datasource.utils.StringUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Durid 配置属性
 */
@Slf4j
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.jdbc")
@ConditionalOnClass(DruidDataSource.class)
public class DruidProperties implements InitializingBean{

    private String driverClass;

    private String url;

    private String username;

    private String password;

    private int initialSize;

    private int minIdle;

    private int maxActive;

    private long maxWait;

    private long timeBetweenEvictionRunsMillis;

    private long minEvictableIdleTimeMillis;

    private String validationQuery;

    private boolean testWhileIdle;

    private boolean testOnBorrow;

    private boolean testOnReturn;

    private boolean poolPreparedStatements;

    private int maxPoolPreparedStatementPerConnectionSize;

    private String filters;

    private String connectionProperties;

    private DruidDataSource write;

    private DruidDataSource read;

    @Override
    public void afterPropertiesSet() throws Exception {

        initDataSourceProperties(write);//maste继承公共属性

        initDataSourceProperties(read);//slave继承公共属性
    }

    private void initDataSourceProperties(DruidDataSource dataSource) throws Exception{
        //继承通用属性
        if (StringUtil.isBlank(dataSource.getDriverClassName())){
            dataSource.setDriverClassName(this.getDriverClass());
        }
        if (StringUtil.isBlank(dataSource.getUrl())){
            dataSource.setUrl(this.getUrl());
        }
        if (StringUtil.isBlank(dataSource.getUsername())){
            dataSource.setUsername(this.getUsername());
        }
        if (StringUtil.isBlank(dataSource.getPassword())){
            dataSource.setPassword(this.getPassword());
        }
        if (dataSource.getInitialSize() == 0 && this.getInitialSize() != 0){
            dataSource.setInitialSize(this.getInitialSize());
        }
        if (dataSource.getMinIdle() == 0 && this.getMinIdle() != 0){
            dataSource.setMinIdle(this.getMinIdle());
        }
        if (dataSource.getMaxActive() == 8 && this.getMaxActive() != 0){
            dataSource.setMaxActive(this.getMaxActive());
        }
        if (dataSource.getMaxWait() == -1 && this.getMaxWait() != 0){
            dataSource.setMaxWait(this.getMaxWait());
        }
        if (dataSource.getTimeBetweenEvictionRunsMillis() == 0){
            dataSource.setTimeBetweenEvictionRunsMillis(this.getTimeBetweenEvictionRunsMillis());
        }
        if (dataSource.getMinEvictableIdleTimeMillis() == 0){
            dataSource.setMinEvictableIdleTimeMillis(this.getMinEvictableIdleTimeMillis());
        }
        if (StringUtil.isBlank(dataSource.getValidationQuery())){
            dataSource.setValidationQuery(this.getValidationQuery());
        }
        if (dataSource.getMaxPoolPreparedStatementPerConnectionSize() == 10
                && this.getMaxPoolPreparedStatementPerConnectionSize() != 0){
            dataSource.setMaxPoolPreparedStatementPerConnectionSize(this.getMaxPoolPreparedStatementPerConnectionSize());
        }
        if (StringUtil.isNotBlank(this.getFilters())){
            dataSource.setFilters(this.getFilters());
        }
        if (StringUtil.isNotBlank(this.getConnectionProperties())){
            dataSource.setConnectionProperties(this.getConnectionProperties());
        }
    }
}
