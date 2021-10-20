package com.qinweizhao.filter;

import com.qinweizhao.service.RedisService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author qinweizhao
 */
public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    public MyUsernamePasswordAuthenticationFilter(RedisService redisService) {


    }
}
