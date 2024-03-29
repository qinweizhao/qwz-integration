package com.qinweizhao.oauth2.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author qinweizhao
 * @since 2022-06-07
 */
@Configuration
public class AccessTokenConfig {

    /**
     * JWT的秘钥
     * 可以统一配置到配置文件中，资源服务也需要用到
     */
    private static final String SIGN_KEY = "qinweizhao";

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }


    /**
     * JwtAccessTokenConverter
     * TokenEnhancer 的子类，在 JWT 编码的令牌值和 OAuth 身份验证信息之间进行转换。
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        // 设置秘钥
        // 此处为对称加密，实际项目需要用非对称加密
        converter.setSigningKey(SIGN_KEY);
        return converter;
    }

}
