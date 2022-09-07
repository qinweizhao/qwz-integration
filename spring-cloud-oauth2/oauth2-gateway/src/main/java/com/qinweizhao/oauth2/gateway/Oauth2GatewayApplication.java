package com.qinweizhao.oauth2.gateway;

import com.qinweizhao.oauth2.gateway.model.SysParameterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author qinweizhao
 * @since 2022/6/7
 */
@SpringBootApplication
@EnableConfigurationProperties(value = {SysParameterConfig.class})
public class Oauth2GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2GatewayApplication.class, args);
    }

}
