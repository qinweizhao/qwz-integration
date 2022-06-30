package com.qinweizhao.oauth2.resource.config;

import com.qinweizhao.oauth2.resource.exception.OAuthResourceAuthenticationEntryPoint;
import com.qinweizhao.oauth2.resource.exception.RequestAccessDeniedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;

/**
 * @author qinweizhao
 * @since 2022/6/7
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {


    @Resource
    private TokenStore tokenStore;

    @Resource
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Bean
    public DefaultTokenServices tokenServices(){
        DefaultTokenServices services = new DefaultTokenServices();
        //配置令牌存储策略，使用AccessTokenConfig配置的JwtTokenStore
        services.setTokenStore(tokenStore);
        //令牌的增强JwtAccessTokenConverter
        services.setTokenEnhancer(jwtAccessTokenConverter);
        return services;
    }

    @Resource
    private RequestAccessDeniedHandler requestAccessDeniedHandler;

    @Resource
    private OAuthResourceAuthenticationEntryPoint authenticationEntryPoint;

    /**
     * 资源服务器安全配置（资源id和令牌校验）
     * @param resources resources
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources
                // 认证失败
                .authenticationEntryPoint(authenticationEntryPoint)
                // 鉴权失败
                .accessDeniedHandler(requestAccessDeniedHandler)
                .resourceId("resource1")
                .tokenServices(tokenServices());
    }


    /**
     * 配置 security 的安全机制
     * @param http http
     * @throws Exception e
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        //#oauth2.hasScope()校验客户端的权限，这个all是在客户端中的scope
        http.authorizeRequests()
                .antMatchers("/**").access("#oauth2.hasScope('all')")
                .anyRequest().authenticated();
    }
}
