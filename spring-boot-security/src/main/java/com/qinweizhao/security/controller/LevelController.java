package com.qinweizhao.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author qinweizhao
 * @since 2021-11-10
 */
@Controller
public class LevelController {

    @PreAuthorize("hasRole('vip1')")
    @RequestMapping("/level1/{num}")
    public String leve1(@PathVariable("num") Integer num) {
        return "views/level1/" + num;
    }

    @PreAuthorize("hasRole('vip2')")
    @RequestMapping("/level2/{num}")
    public String leve2(@PathVariable("num") Integer num) {
        return "views/level2/" + num;
    }

    @PreAuthorize("hasRole('vip3')")
    @RequestMapping("/level3/{num}")
    public String leve3(@PathVariable("num") Integer num) {
        return "views/level3/" + num;
    }

}
