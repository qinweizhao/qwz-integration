package com.qinweizhao.oauth2.gateway.model;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author qinweizhao
 * @since 2022-06-07
 */
@Data
@ConfigurationProperties(prefix = "oauth2.cloud.sys.parameter")
public class SysParameterConfig {
    /**
     * 白名单
     */
    private List<String> ignoreUrls;
}
