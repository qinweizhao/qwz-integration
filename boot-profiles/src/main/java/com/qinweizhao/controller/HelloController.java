package com.qinweizhao.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qinweizhao
 * @since 2021/9/10
 */
@RestController
public class HelloController {

    @GetMapping("hello")
    public String hello(){
        return "Hello SpringBoot";
    }
}
