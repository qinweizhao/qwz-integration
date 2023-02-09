package com.qinweizhao.redisson.spring.boot;

import org.apache.juli.logging.Log;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.ws.RespectBinding;

@SpringBootTest
class RedissonApplicationTests {

    @Autowired
    RedissonClient redisson;

    @Test
    void contextLoads() {
        RLock lock = redisson.getLock("test-lock");
        boolean b = lock.tryLock();
        if (b){
            System.out.println(b?"成功":"失败");
        }
    }

}
