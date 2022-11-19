package com.qinweizhao.elasticjob.lite.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.infra.listener.ShardingContexts;
import org.apache.shardingsphere.elasticjob.lite.api.listener.AbstractDistributeOnceElasticJobListener;

/**
 * @author qinweizhao
 * @since 2022/10/19
 */
@Slf4j
public class MyDistributeOnceJobListener extends AbstractDistributeOnceElasticJobListener {

    public MyDistributeOnceJobListener(long startedTimeoutMilliseconds, long completedTimeoutMilliseconds) {
        super(startedTimeoutMilliseconds, completedTimeoutMilliseconds);
    }

    @Override
    public void doBeforeJobExecutedAtLastStarted(ShardingContexts shardingContexts) {
        log.debug("分布式监听器开始");
        log.debug("作业执行前{}", shardingContexts);
    }

    @Override
    public void doAfterJobExecutedAtLastCompleted(ShardingContexts shardingContexts) {
        log.debug("作业执行后{}", shardingContexts);
        log.debug("分布式监听器结束");
    }

    @Override
    public String getType() {
        return "distributeOnceJobListener";
    }

}
