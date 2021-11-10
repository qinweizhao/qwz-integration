package com.qinweizhao.config.swagger2;

import com.qinweizhao.interceptor.MyInterceptor;
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
//@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 配置swagger拦截器
        registry.addInterceptor(new MyInterceptor())// 这里加入自己写的拦截器
                .addPathPatterns("/**").
                excludePathPatterns("/swagger-resources/**", "/webjars/**", "/swagger-ui.html/**", "/v2/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 配置swagger静态资源映射
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 跨域支持
        registry.addMapping("/**").allowedOriginPatterns("*").allowedMethods("*").allowCredentials(true);
    }

}

