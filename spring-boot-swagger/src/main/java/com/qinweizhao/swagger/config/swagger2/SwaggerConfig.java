package com.qinweizhao.swagger.config.swagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author qinweizhao
 * @since 2021/11/10
 */
//@Configuration
//@EnableSwagger2
public class SwaggerConfig {


    // *** 基础配置 ***

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 自行修改为自己的包路径
                .apis(RequestHandlerSelectors.basePackage("com.qinweizhao"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("swagger-api文档")
                .description("swagger接入教程")
                .version("1.0")
                .contact(new Contact("qinweizhao", "https://www.qinweizhao.com", "yvkg@foxmail.com"))
                .build();
    }


    // *** Token验证配置 ***

//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                // 自行修改为自己的包路径
//                .apis(RequestHandlerSelectors.basePackage("com.qinweizhao"))
//                .paths(PathSelectors.any())
//                .build()
//                .securitySchemes(securitySchemes())
//                .securityContexts(securityContexts());
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("swagger-api文档")
//                .description("swagger接入教程")
//                .version("1.0")
//                .contact(new Contact("qinweizhao", "https://www.qinweizhao.com", "yvkg@foxmail.com"))
//                .build();
//    }
//
//    private List<ApiKey> securitySchemes() {
//        List<ApiKey> apiKeyList = new ArrayList();
//        apiKeyList.add(new ApiKey("tokenName", "token", "header"));
//        return apiKeyList;
//    }
//
//    private List<SecurityContext> securityContexts() {
//        List<SecurityContext> securityContexts = new ArrayList<>();
//        securityContexts.add(SecurityContext.builder()
//                .securityReferences(defaultAuth())
//                .forPaths(PathSelectors.regex("^(?!auth).*$"))
//                .build());
//        return securityContexts;
//    }
//
//    List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        List<SecurityReference> securityReferences = new ArrayList<>();
//        securityReferences.add(new SecurityReference("token", authorizationScopes));
//        return securityReferences;
//    }


}
