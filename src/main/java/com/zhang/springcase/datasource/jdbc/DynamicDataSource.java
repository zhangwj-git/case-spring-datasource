package com.zhang.springcase.datasource.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态获取数据源
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource{

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setWrite(){
        contextHolder.set(DataSourceType.WRITE.getTypeName());
    }

    public static void setRead(){
        contextHolder.set(DataSourceType.READ.getTypeName());
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSouceName = contextHolder.get();
        log.info("DataSource is ==> {}",dataSouceName);
        return dataSouceName;
    }
}
