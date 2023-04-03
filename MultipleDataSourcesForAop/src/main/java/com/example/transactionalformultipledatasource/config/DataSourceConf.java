package com.example.transactionalformultipledatasource.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * @author: Rrow
 * @date: 2023/4/3 20:39
 * Description:
 */
@Configuration
public class DataSourceConf {

    @Autowired
    private Environment env;

    // 封装了3个数据源的方法
    @Bean
    public DataSource dynamicDataSource(){
        // 创建3个数据源
        DataSource dataSource = datasource();
        DataSource secondDataSource = secondDataSource();
        DataSource thirdDataSource = thirdDataSource();

        // 存储3个数据源
        HashMap<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DynamicDataSource.DataBaseType.DATASOURCE, dataSource);
        targetDataSource.put(DynamicDataSource.DataBaseType.SECOND_DATASOURCE, secondDataSource);
        targetDataSource.put(DynamicDataSource.DataBaseType.THIRD_DATASOURCE, thirdDataSource);

        // 创建动态数据源
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(targetDataSource);
        dynamicDataSource.setDefaultTargetDataSource(dataSource);
        return dynamicDataSource;
    }

    public DataSource datasource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(env.getProperty("spring.datasource.jdbc-url"));
        ds.setUsername(env.getProperty("spring.datasource.username"));
        ds.setPassword(env.getProperty("spring.datasource.password"));
        ds.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        return ds;
    }

    public DataSource secondDataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(env.getProperty("spring.second-datasource.jdbc-url"));
        ds.setUsername(env.getProperty("spring.second-datasource.username"));
        ds.setPassword(env.getProperty("spring.second-datasource.password"));
        ds.setDriverClassName(env.getProperty("spring.second-datasource.driver-class-name"));
        return ds;
    }

    public DataSource thirdDataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(env.getProperty("spring.third-datasource.jdbc-url"));
        ds.setUsername(env.getProperty("spring.third-datasource.username"));
        ds.setPassword(env.getProperty("spring.third-datasource.password"));
        ds.setDriverClassName(env.getProperty("spring.third-datasource.driver-class-name"));
        return ds;
    }

}
