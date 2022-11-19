package com.qinweizhao.elasticjob.lite.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.infra.listener.ElasticJobListener;
import org.apache.shardingsphere.elasticjob.infra.listener.ShardingContexts;

/**
 * @author qinweizhao
 * @since 2022/10/19
 */
@Slf4j
public class MyJobListener implements ElasticJobListener {

    @Override
    public void beforeJobExecuted(ShardingContexts shardingContexts) {
        log.info("常规监听器开始");
        log.info("作业执行前{}", shardingContexts);
    }

    @Override
    public void afterJobExecuted(ShardingContexts shardingContexts) {
        log.info("作业执行后{}", shardingContexts);
        log.info("常规监听器结束");
    }

    @Override
    public String getType() {
        return "myJobListener";
    }

}
