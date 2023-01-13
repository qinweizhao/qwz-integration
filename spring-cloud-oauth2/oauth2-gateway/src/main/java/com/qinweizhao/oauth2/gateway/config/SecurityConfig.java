package com.qinweizhao.oauth2.gateway.config;

import cn.hutool.core.util.ArrayUtil;
import com.qinweizhao.oauth2.gateway.exception.RequestAccessDeniedHandler;
import com.qinweizhao.oauth2.gateway.exception.RequestAuthenticationEntryPoint;
import com.qinweizhao.oauth2.gateway.filter.CorsFilter;
import com.qinweizhao.oauth2.gateway.model.SysParameterConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.web.server.ServerBearerTokenAuthenticationConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authorization.AuthorizationContext;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qinweizhao
 * @since 2022-06-07
 * 网关的OAuth2.0资源的配置类
 * 由于gateway使用的Flux，因此需要使用@EnableWebFluxSecurity注解开启，而不是平常的web应用了
 */
@Slf4j
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {


    /**
     * token过期的异常处理
     */
    @Resource
    private RequestAuthenticationEntryPoint requestAuthenticationEntryPoint;

    /**
     * 权限不足的异常处理
     */
    @Resource
    private RequestAccessDeniedHandler requestAccessDeniedHandler;

    /**
     * 系统参数配置
     */
    @Resource
    private SysParameterConfig sysConfig;

    /**
     * 鉴权管理器
     */
    @Resource
    private ReactiveAuthorizationManager<AuthorizationContext> accessManager;

    /**
     * 认证管理器
     */
    @Resource
    private ReactiveAuthenticationManager myAuthenticationManager;

    /**
     * 跨域过滤器
     */
    @Resource
    private CorsFilter corsFilter;

    @Bean
    SecurityWebFilterChain webFluxSecurityFilterChain(ServerHttpSecurity http) {
        //认证过滤器，放入认证管理器tokenAuthenticationManager
        AuthenticationWebFilter authenticationWebFilter = new AuthenticationWebFilter(myAuthenticationManager);
        authenticationWebFilter.setServerAuthenticationConverter(new ServerBearerTokenAuthenticationConverter());
        List<String> ignoreUrls = sysConfig.getIgnoreUrls();
        log.info("ignoreUrls" + ignoreUrls);

        http.httpBasic().disable().csrf().disable().authorizeExchange()
                //白名单直接放行
                .pathMatchers(ArrayUtil.toArray(sysConfig.getIgnoreUrls(), String.class)).permitAll()
                //其他的请求必须鉴权，使用鉴权管理器
                .anyExchange().access(accessManager)
                //鉴权的异常处理，权限不足，token失效
                .and().exceptionHandling().authenticationEntryPoint(requestAuthenticationEntryPoint).accessDeniedHandler(requestAccessDeniedHandler).and()
                // 跨域过滤器
//                .addFilterAt(corsFilter, SecurityWebFiltersOrder.CORS)
                //token的认证过滤器，用于校验token和认证
                .addFilterAt(authenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION);
        return http.build();
    }

}