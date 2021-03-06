package com.qinweizhao;

import com.qinweizhao.redis.service.HelloService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisApplicationTests {

    @Autowired
    HelloService helloService;

    @Test
    void contextLoads() {
        helloService.hello();
    }

}
