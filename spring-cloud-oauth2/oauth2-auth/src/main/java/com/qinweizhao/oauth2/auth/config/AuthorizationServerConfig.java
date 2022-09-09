package com.qinweizhao.oauth2.auth.config;

import com.qinweizhao.oauth2.auth.excepion.AuthServerAuthenticationEntryPoint;
import com.qinweizhao.oauth2.auth.excepion.AuthServerWebResponseExceptionTranslator;
import com.qinweizhao.oauth2.auth.filter.AuthServerClientCredentialsTokenEndpointFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;

/**
 * @author qinweizhao
 * @since 2022/6/7
 */
@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Resource
    private AuthServerAuthenticationEntryPoint authenticationEntryPoint;

    /**
     * 认证服务器安全配置（令牌访问的安全约束）
     *
     * @param security security
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        // 自定义 ClientCredentialsTokenEndpointFilter，用于处理客户端id，密码错误的异常
        AuthServerClientCredentialsTokenEndpointFilter endpointFilter = new AuthServerClientCredentialsTokenEndpointFilter(security, authenticationEntryPoint);
        endpointFilter.afterPropertiesSet();

        security.addTokenEndpointAuthenticationFilter(endpointFilter);

        security
                // 开启 /oauth/token_key 验证端口权限访问
                .tokenKeyAccess("permitAll()")
                // 开启 /oauth/check_token 验证端口权限访问
                .checkTokenAccess("permitAll()");
    }

    /**
     * 客户端详情配置
     *
     * @param clients clients
     * @throws Exception e
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 内存模式
        clients.inMemory()
                // 客户端 id
                .withClient("c1")
                // 客户端密钥
                .secret(new BCryptPasswordEncoder().encode("123"))
                // 资源 id ， 唯一，一个服务即一个资源，可以设置多个
                .resourceIds("resource1")
                // 授权类型  总共四种，1. authorization_code（授权码模式）、password（密码模式）、client_credentials（客户端模式）、implicit（简化模式）
                // refresh_token并不是授权模式，
                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token")
                // 允许的授权范围，客户端的权限，这里的 all 是一种标识，可以自定义，为了后续的资源服务进行权限控制
                .scopes("all")
                // false 则跳转到授权页面
                .autoApprove(true)
                // 回掉的地址
                .redirectUris("https://www.qinweizhao.com");
    }


    /**
     * Security配置类注入
     */
    @Resource
    private AuthenticationManager authenticationManager;

    /**
     * 授权码模式的service，使用授权码模式必须注入
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new InMemoryAuthorizationCodeServices();
    }


    /**
     * 客户端存储策略，这里使用内存方式，后续可以存储在数据库
     */
    @Resource
    private ClientDetailsService clientDetailsService;

    /**
     * 令牌存储策略
     */
    @Resource
    private TokenStore tokenStore;

    /**
     * JWT 编码的令牌值和 OAuth 身份验证信息之间进行转换
     */
    @Resource
    private JwtAccessTokenConverter jwtAccessTokenConverter;


    /**
     * 令牌管理服务的配置
     */
    @Bean
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices services = new DefaultTokenServices();
        // 客户端端配置策略
        services.setClientDetailsService(clientDetailsService);
        // 支持令牌的刷新
        services.setSupportRefreshToken(true);
        // 令牌服务
        services.setTokenStore(tokenStore);
        // access_token的过期时间
        services.setAccessTokenValiditySeconds(60 * 60 * 2);
        // refresh_token的过期时间
        services.setRefreshTokenValiditySeconds(60 * 60 * 24 * 3);

        // * 令牌增强
        services.setTokenEnhancer(jwtAccessTokenConverter);
        return services;
    }


    /**
     * 认证服务器端点配置（令牌访问的端点）
     *
     * @param endpoints endpoints
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                // 1、设置异常 WebResponseExceptionTranslator，用于处理用户名，密码错误、授权类型不正确的异常
                .exceptionTranslator(new AuthServerWebResponseExceptionTranslator())
                // 2、授权码模式所需要的 service
                .authorizationCodeServices(authorizationCodeServices())
                // 3、密码模式需要的认证管理器
                .authenticationManager(authenticationManager)
                // 4、令牌管理服务，必须存在
                .tokenServices(tokenServices())
                // 5、提交访问令牌（/oauth/token）只允许 POST 请求
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }


}
