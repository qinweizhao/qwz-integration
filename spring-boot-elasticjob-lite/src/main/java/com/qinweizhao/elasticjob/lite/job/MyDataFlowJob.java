package com.qinweizhao.elasticjob.lite.job;

import com.qinweizhao.elasticjob.lite.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.dataflow.job.DataflowJob;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @author qinweizhao
 * @since 2022/10/17
 */
@Slf4j
@Component
public class MyDataFlowJob implements DataflowJob<User> {

    @Override
    public List<User> fetchData(ShardingContext shardingContext) {
        log.debug("获取数据,分片项为"+shardingContext.getShardingItem());
        User user = new User(1, "qwz", "123", "china");
        return Collections.singletonList(user);
    }

    @Override
    public void processData(ShardingContext shardingContext, List<User> list) {
        log.debug("开始处理数据");
        list.forEach(System.out::println);
    }

}
