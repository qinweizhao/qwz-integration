package com.qinweizhao.config;


import com.qinweizhao.filter.MyFilter;
import com.qinweizhao.filter.MyUsernamePasswordAuthenticationFilter;
import com.qinweizhao.handler.MyAccessDeniedHandler;
import com.qinweizhao.handler.MyAuthenticationSuccessHandler;
import com.qinweizhao.handler.MyLogoutSuccessHandler;
import com.qinweizhao.handler.MyUrlAuthenticationFailureHandler;
import com.qinweizhao.manager.MyExpressionVoter;
import com.qinweizhao.service.RedisService;
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
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;


/**
 * @author qinweizhao
 * @since 2021/10/20
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private UserDetailsService userDetailService;

    @Resource
    private RedisService redisService;


    /**
     * @return AuthenticationManager 认证管理器
     */
    @Override
    public AuthenticationManager authenticationManagerBean() {
        return new ProviderManager(Arrays.asList(getMyAuthenticationProvider(), daoAuthenticationProvider()));
    }

    private AuthenticationProvider daoAuthenticationProvider() {
        return null;
    }

    private AuthenticationProvider getMyAuthenticationProvider() {
        return null;
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
        auth.userDetailsService(userDetailService).passwordEncoder(bCryptPasswordEncoder());
    }

    /**
     * 强散列哈希加密实现
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * 配置静态资源的处理方式，可使用 Ant 匹配规则
     *
     * @param web web
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/js");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //配置一个 /test url 该有什么权限才能访问，anyRequest() 表示所有请求，authenticated() 表示已登录用户才能访问。
                // accessDecisionManager() 表示绑定在 url 上的鉴权管理器
                .and().authorizeRequests().antMatchers("/test").hasRole("test")
                .anyRequest().authenticated().accessDecisionManager(accessDecisionManager())
                //登出 url 和登出成功处理器:
                .and().logout().logoutSuccessHandler(new MyLogoutSuccessHandler())
                //CSRF禁用，因为不使用session
                .and().csrf().disable();
        //配置鉴权失败的处理器
        http.exceptionHandling().accessDeniedHandler(new MyAccessDeniedHandler());
        //插入自己的过滤器，addFilterBefore 加在对应的过滤器之前
        // addFilterAfter 加在对应的过滤器之后
        // addFilterAt 加在过滤器同一位置
        // 事实上框架原有的 Filter 在启动 HttpSecurity 配置的过程中，都由框架完成了其一定程度上固定的配置，是不允许更改替换的。
        // 根据测试结果来看，调用 addFilterAt 方法插入的 Filter ，会在这个位置上的原有 Filter 之前执行

        http.addFilterAt(getAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.addFilterAfter(new MyFilter(), LogoutFilter.class);
    }

    private MyUsernamePasswordAuthenticationFilter getAuthenticationFilter() {

        MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter = new MyUsernamePasswordAuthenticationFilter(redisService);
        myUsernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(new MyUrlAuthenticationFailureHandler());
        myUsernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(new MyAuthenticationSuccessHandler());
        myUsernamePasswordAuthenticationFilter.setFilterProcessesUrl("/sign_in");
        myUsernamePasswordAuthenticationFilter.setAuthenticationManager(getAuthenticationManager());
        return myUsernamePasswordAuthenticationFilter;

    }

    private AuthenticationManager getAuthenticationManager() {
        return this.authenticationManagerBean();
    }


}
