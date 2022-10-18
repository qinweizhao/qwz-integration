package com.qinweizhao.elasticjob.lite.config;

import org.apache.shardingsphere.elasticjob.lite.spring.boot.reg.ZookeeperProperties;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperConfiguration;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


/**
 * @author qinweizhao
 * @since 2022/10/17
 */
@Configuration
public class ElasticJobConfig {


    /**
     * 可以不配置
     * 配置  zookeeper 注册中心
     */
    @Primary
    @Bean(initMethod = "init")  // 需要配置init执行初始化逻辑
    public ZookeeperRegistryCenter regCenter(ZookeeperProperties zookeeperProperties) {
        String serverList = zookeeperProperties.getServerLists();
        String namespace = zookeeperProperties.getNamespace();
        ZookeeperConfiguration zookeeperConfiguration = new ZookeeperConfiguration(serverList, namespace);
        // 重试次数
        zookeeperConfiguration.setMaxRetries(3);
        // 会话超时时间
        zookeeperConfiguration.setSessionTimeoutMilliseconds(500000);
        return new ZookeeperRegistryCenter(zookeeperConfiguration);
    }


}
