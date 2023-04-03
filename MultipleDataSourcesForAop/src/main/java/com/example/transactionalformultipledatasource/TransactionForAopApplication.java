package com.example.transactionalformultipledatasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.transactionalformultipledatasource.mapper.**")
public class TransactionForAopApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionForAopApplication.class, args);
    }

}
