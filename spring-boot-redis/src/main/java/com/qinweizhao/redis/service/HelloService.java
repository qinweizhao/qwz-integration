package com.qinweizhao.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 * @author qinweizhao
 * @since 2021/10/18
 */
@Service
public class HelloService {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    public void hello(){
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set("k","v");
        Object k = ops.get("k");
        System.out.println("k = " + k);
    }

}
