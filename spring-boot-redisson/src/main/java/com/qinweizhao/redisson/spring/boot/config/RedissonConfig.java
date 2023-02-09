package com.qinweizhao.redisson.spring.boot.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author qinweizhao
 * @since 2023-02-09
 */
@Configuration
public class RedissonConfig {

    /**
     * 所有对 Redisson 的使用都是通过 RedissonClient 对象
     * @return RedissonClient
     */
    @Bean(destroyMethod="shutdown")
    public RedissonClient redisson(@Value("${spring.redis.host}") String url) {
        //1、创建配置
        //Redis url should start with redis:// or rediss://
        Config config = new Config();
        config.useSingleServer().setAddress("redis://"+url+":6379");
        //2、根据Config创建出RedissonClient示例
        return Redisson.create(config);
    }
}
