package com.qinweizhao.elasticjob.lite.job;

import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.stereotype.Component;

/**
 * @author qinweizhao
 * @since 2022/10/17
 */
@Component
public class OccurErrorNoticeWechatJob implements SimpleJob {

    @Override
    public void execute(final ShardingContext shardingContext) {
        throw new RuntimeException(String.format("作业出现异常, 参数为 %s", shardingContext.getShardingParameter()));
    }

}
