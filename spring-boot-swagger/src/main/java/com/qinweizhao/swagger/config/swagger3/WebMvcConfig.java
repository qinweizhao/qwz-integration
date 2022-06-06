package com.qinweizhao.swagger.config.swagger3;

import com.qinweizhao.swagger.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 若配置有拦截器，应对Swagger做如下配置
 *
 * @author qinweizhao
 * @since 2021/11/9
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 如果有 security 一定要放行 /swagger-resources/**", "/webjars/**", "/swagger-ui/**", "/v3/**");
     *
     * @param registry registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 配置swagger拦截器
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/**").
                excludePathPatterns("/swagger-resources/**", "/webjars/**", "/swagger-ui/**", "/v3/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 配置swagger静态资源映射
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 跨域支持
        registry.addMapping("/**").allowedOriginPatterns("*").allowedMethods("*").allowCredentials(true);
    }

}

