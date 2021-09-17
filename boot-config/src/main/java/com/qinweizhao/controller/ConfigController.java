package com.qinweizhao.controller;

import com.qinweizhao.entity.Person;
import com.qinweizhao.entity.Police;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author qinweizhao
 * @since 2021/9/10
 */
@RestController
public class ConfigController {

    @Resource
    private Person person;

    @Resource
    private Police police;

    /**
     * 测试方法
     *
     * @return s
     */
    @GetMapping("hello")
    public String hello() {
        return "Hello SpringBoot";
    }

    /**
     * SpringBoot 方式获取值
     *
     * @return p
     */
    @GetMapping("/person")
    public Person person() {
        return person;
    }

    /**
     * Spring 方式获取值
     *
     * @return u
     */
    @GetMapping("/user")
    public Police user() {
        return police;
    }
}
