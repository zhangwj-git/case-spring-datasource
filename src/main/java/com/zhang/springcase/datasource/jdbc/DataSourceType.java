package com.zhang.springcase.datasource.jdbc;

/**
 * 数据源类型
 */
public enum  DataSourceType {

    WRITE("writeDataSource"),//写库
    READ("readDataSource");//读库

    private String typeName;

    DataSourceType(String dataSource){
        this.typeName = dataSource;
    }

    public String getTypeName(){
        return this.typeName;
    }
}
