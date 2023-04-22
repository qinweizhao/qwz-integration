package com.qinweizhao.integration.security.config;

import com.qinweizhao.integration.security.handler.MyAuthenticationEntryPoint;
import com.qinweizhao.integration.security.handler.MyLogoutSuccessHandler;
import com.qinweizhao.integration.security.provider.MyAuthenticationProvider;
import com.qinweizhao.integration.security.voter.MyExpressionVoter;
import com.qinweizhao.integration.security.filter.MyFilter;
import com.qinweizhao.integration.security.handler.MyAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import java.util.Arrays;
import java.util.List;

/**
 * @author qinweizhao
 * @since 2021-11-10
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 放行的资源
     */
    private static final String[] URL_WHITELIST = {
            "/css/**",
            "/js/**",
            "/favicon.ico"
    };


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    @Autowired
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;

    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;

    /**
     * 密码编码器
     *
     * @return BCryptPasswordEncoder
     */
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 认证管理器
     *
     * @return AuthenticationManager
     */
    @Override
    public AuthenticationManager authenticationManagerBean() {
        return new ProviderManager(Arrays.asList(daoAuthenticationProvider()
                , myAuthenticationProvider()
        ));
    }

    /**
     * 认证提供者
     *
     * @return AuthenticationProvider
     */
    private AuthenticationProvider daoAuthenticationProvider() {
        return new DaoAuthenticationProvider();
    }

    /**
     * 自定义认证提供者
     *
     * @return AuthenticationProvider
     */
    private AuthenticationProvider myAuthenticationProvider() {
        return new MyAuthenticationProvider();
    }

    /**
     * (授权)
     *
     * @return 投票管理器
     */
    private AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<?>> decisionVoters
                = Arrays.asList(
                new MyExpressionVoter(),
                new WebExpressionVoter(),
                new RoleVoter(),
                new AuthenticatedVoter());
        return new UnanimousBased(decisionVoters);
    }

    /**
     * 配置 AuthenticationManagerBuilder 会让 Security 自动构建一个 AuthenticationManager；
     * 如果想要使用该功能你需要配置一个 UserDetailService 和 PasswordEncoder。UserDetailsService 用于在认证器中根据用户传过来的用户名查找一个用户
     * PasswordEncoder 用于密码的加密与比对，我们存储用户密码的时候用PasswordEncoder.encode() 加密存储，在认证器里会调用 PasswordEncoder.matches() 方法进行密码比对。
     * 如果重写了该方法，Security 会启用 DaoAuthenticationProvider 这个认证器，该认证就是先调用 UserDetailsService.loadUserByUsername
     * 然后使用 PasswordEncoder.matches() 进行密码比对，如果认证成功成功则返回一个 Authentication 对象
     *
     * @param auth auth
     * @throws Exception exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    /**
     * 配置静态资源的处理方式，可使用 Ant 匹配规则
     *
     * @param web web
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(URL_WHITELIST);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .csrf().disable()


                // 登录
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                // 如果不放行则会出现无限重定向
                .permitAll()


                // 注销
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .logoutSuccessHandler(myLogoutSuccessHandler)


                // 记住我
                .and()
                .rememberMe()
                .rememberMeParameter("remember")


                .and()
                .authorizeRequests()
                .antMatchers("/")
                .permitAll()
                .anyRequest()
                .authenticated()
                // 指定投票器
                .accessDecisionManager(accessDecisionManager())


                .and()
                .exceptionHandling()
                .authenticationEntryPoint(myAuthenticationEntryPoint)
                .accessDeniedHandler(myAccessDeniedHandler)

                .and()
                .addFilterAfter(new MyFilter(), LogoutFilter.class);
    }
}
