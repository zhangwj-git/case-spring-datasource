package com.zhang.springcase.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.zhang.springcase.datasource.jdbc.DataSourceType;
import com.zhang.springcase.datasource.jdbc.DruidProperties;
import com.zhang.springcase.datasource.jdbc.DynamicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源配置
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

    @Autowired
    private DruidProperties druidPropertite;

    @Bean("writeDataSource")
    public DruidDataSource getMasterDataSource(){
        return druidPropertite.getWrite();
    }

    @Bean("readDataSource")
    public DruidDataSource getSlaveDataSouce(){
        return druidPropertite.getRead();
    }

    @Bean
    @Primary
    public DynamicDataSource dynamicDataSource(
            @Qualifier("writeDataSource") DruidDataSource write,
            @Qualifier("readDataSource") DruidDataSource read){
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setDefaultTargetDataSource(write);
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.WRITE.getTypeName(),write);
        targetDataSources.put(DataSourceType.READ.getTypeName(),read);
        dataSource.setTargetDataSources(targetDataSources);
        dataSource.setDefaultTargetDataSource(read);
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager dataSourceTransactionManager(DataSource dynamicDataSource){
        return new DataSourceTransactionManager(dynamicDataSource);
    }
}
