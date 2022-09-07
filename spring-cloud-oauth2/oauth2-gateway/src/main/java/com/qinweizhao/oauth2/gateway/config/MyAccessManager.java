package com.qinweizhao.oauth2.gateway.config;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.CharSequenceUtil;
import com.qinweizhao.oauth2.gateway.model.SysConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 鉴权管理器
 * 对用户的权限进行鉴权
 * 逻辑：从redis中获取对应的uri的权限，与当前用户的token的携带的权限进行对比，如果包含则鉴权成功
 * 企业中可能有不同的处理逻辑，可以根据业务需求更改鉴权的逻辑
 *
 * @author qinweizhao
 * @since 2022/6/7
 */
@Slf4j
@Component
public class MyAccessManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        // 匹配url
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        //从Redis中获取当前路径可访问角色列表
        URI uri = authorizationContext.getExchange().getRequest().getURI();
        //请求方法 POST,GET
        String method = authorizationContext.getExchange().getRequest().getMethodValue();
        // 适配restful接口，比如 GET:/api/.... POST:/api/....  *:/api/.....  星号匹配所有
        String restFulPath = method + SysConstant.METHOD_SUFFIX + uri.getPath();
        //获取所有的uri->角色对应关系
        Map<String, List<String>> entries = redisTemplate.opsForHash().entries(SysConstant.OAUTH_URLS);
        //角色集合
        List<String> authorities = new ArrayList<>();
        entries.forEach((path, roles) -> {
            //路径匹配则添加到角色集合中
            if (antPathMatcher.match(path, restFulPath)) {
                authorities.addAll(roles);
            }
        });
        //认证通过且角色匹配的用户可访问当前路径
        return mono
                //判断是否认证成功
                .filter(Authentication::isAuthenticated)
                //获取认证后的全部权限
                .flatMapIterable(Authentication::getAuthorities).map(GrantedAuthority::getAuthority)
                //如果权限包含则判断为true
                .any(authority -> {
                    //超级管理员直接放行
                    if (CharSequenceUtil.equals(SysConstant.ROLE_ROOT_CODE, authority)) {
                        return true;
                    }
                    //其他必须要判断角色是否存在交集
                    return CollUtil.isNotEmpty(authorities) && authorities.contains(authority);
                }).map(AuthorizationDecision::new).defaultIfEmpty(new AuthorizationDecision(false));
    }


}