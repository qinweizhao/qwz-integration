package com.qinweizhao.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qinweizhao
 * @since 2021/9/7
 */
@RestController
public class LoggingController {

    @GetMapping("/test")
    public String test(){
        return "test";
    }

}
