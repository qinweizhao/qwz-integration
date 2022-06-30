package com.qinweizhao.oauth2.resource.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qinweizhao
 * @since 2022/6/7
 */
@RestController
public class HelloController {

    /**
     * 无权限拦截，认证成功都可以访问
     */
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    /**
     * ROLE_admin 的角色才可以访问
     */
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_admin')")
    public String admin() {
        return "admin";
    }
}
