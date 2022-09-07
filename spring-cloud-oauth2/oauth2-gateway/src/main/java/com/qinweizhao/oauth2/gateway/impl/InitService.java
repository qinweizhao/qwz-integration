package com.qinweizhao.oauth2.gateway.impl;

import cn.hutool.core.collection.CollUtil;
import com.qinweizhao.oauth2.gateway.model.SysConstant;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 用于初始化uir的权限到redis中
 */
@Service
public class InitService {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @PostConstruct
    public void init() {
        redisTemplate.opsForHash().put(SysConstant.OAUTH_URLS, "/resource/hello", CollUtil.newArrayList("ROLE_user"));
        redisTemplate.opsForHash().put(SysConstant.OAUTH_URLS, "/resource/admin", CollUtil.newArrayList("ROLE_admin"));
    }
}
