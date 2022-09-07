package com.qinweizhao.oauth2.gateway.impl;

import com.google.common.collect.Lists;
import com.qinweizhao.oauth2.common.model.SysConstant;
import org.springframework.beans.factory.annotation.Autowired;
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
    private RedisTemplate<String,Object> redisTemplate;

    @PostConstruct
    public void init(){
        redisTemplate.opsForHash().put(SysConstant.OAUTH_URLS,"/order/login/info", Lists.newArrayList("ROLE_admin","ROLE_user"));
        redisTemplate.opsForHash().put(SysConstant.OAUTH_URLS,"/order/login/admin", Lists.newArrayList("ROLE_admin"));
        redisTemplate.opsForHash().put(SysConstant.OAUTH_URLS,"/order/info", Lists.newArrayList("ROLE_admin","ROLE_user"));
        redisTemplate.opsForHash().put(SysConstant.OAUTH_URLS,"/order/listByUserId", Lists.newArrayList("ROLE_admin","ROLE_user"));
        redisTemplate.opsForHash().put(SysConstant.OAUTH_URLS,"/oauth/logout", Lists.newArrayList("ROLE_admin","ROLE_user"));
    }

}
