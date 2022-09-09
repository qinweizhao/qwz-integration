package com.qinweizhao.oauth2.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author qinweizhao
 */
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.qinweizhao.oauth2.auth.*"})
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

}
