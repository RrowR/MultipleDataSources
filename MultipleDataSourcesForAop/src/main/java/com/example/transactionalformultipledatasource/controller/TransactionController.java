package com.example.transactionalformultipledatasource.controller;

import com.example.transactionalformultipledatasource.anno.FirstSource;
import com.example.transactionalformultipledatasource.anno.SecondSource;
import com.example.transactionalformultipledatasource.anno.ThirdSource;
import com.example.transactionalformultipledatasource.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Rrow
 * @Date 2023/4/3 16:34
 */
@RestController
public class TransactionController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/testFirstInsert")
    @FirstSource
    public String testFirstInsert(String username, String password) {
        userMapper.insert(username, password);
        return "finish";
    }

    @GetMapping("/testSecondInsert")
    @SecondSource
    public String testSecondInsert(String username, String password) {
        userMapper.insert(username, password);
        return "finish";
    }

    @GetMapping("/testThirdInsert")
    @ThirdSource
    public String testThirdInsert(String username, String password) {
        userMapper.insert(username, password);
        return "finish";
    }
}
