package com.qinweizhao.config.swagger3;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author qinweizhao
 * @since 2021/10/12
 */
@EnableOpenApi
@Configuration
public class SwaggerConfig {


    // *** 基础配置 ***

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.qinweizhao"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Api Documentation")
                .contact(new Contact("qinweizhao", "http://www.baidu.com", "yvkg@foxmail.com"))
                .version("1.0")
                .build();
    }

    // *** Token验证配置 ***

//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.OAS_30)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.qinweizhao"))
//                .paths(PathSelectors.any())
//                .build()
//                .protocols(newHashSet("https", "http"))
//                .securitySchemes(securitySchemes())
//                .securityContexts(securityContexts());
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("Api Documentation")
//                .contact(new Contact("qinweizhao", "http://www.baidu.com", "yvkg@foxmail.com"))
//                .version("1.0")
//                .build();
//    }
//
//    /**
//     * 设置授权信息
//     */
//    private List<SecurityScheme> securitySchemes() {
//        ApiKey apiKey = new ApiKey("tokenName", "token", In.HEADER.toValue());
//        return Collections.singletonList(apiKey);
//    }
//
//    /**
//     * 授权信息全局应用
//     */
//    private List<SecurityContext> securityContexts() {
//        return Collections.singletonList(
//                SecurityContext.builder()
//                        .securityReferences(Collections.singletonList(new SecurityReference("token", new AuthorizationScope[]{new AuthorizationScope("global", "accessEverything")})))
//                        .build()
//        );
//    }
//
//    @SafeVarargs
//    private final <T> Set<T> newHashSet(T... ts) {
//        if (ts.length > 0) {
//            return new LinkedHashSet<>(Arrays.asList(ts));
//        }
//        return new HashSet<>();
//    }


}

