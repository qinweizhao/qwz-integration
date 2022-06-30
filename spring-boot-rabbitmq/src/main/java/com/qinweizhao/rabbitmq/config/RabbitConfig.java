package com.qinweizhao.rabbitmq.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author qinweizhao
 * @since 2022/6/30
 */
@Configuration
public class RabbitConfig implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback {

    private static final Logger logger = LoggerFactory.getLogger(RabbitConfig.class);

    @Resource
    RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void initRabbitTemplate() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
    }

    /**
     * Publisher -> Exchange
     *
     * @param correlationData correlationData
     * @param ack             ack
     * @param cause           cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {

        if (ack) {
            logger.info("{}:消息成功到达交换器", correlationData.getId());
        } else {
            logger.error("{}:消息发送失败", correlationData.getId());
        }

    }

    /**
     * Exchange -xxx- Queue
     *
     * @param returned returned
     */
    @Override
    public void returnedMessage(ReturnedMessage returned) {
        logger.error("{}:消息未成功路由到队列", returned.getMessage().getMessageProperties().getMessageId());
    }
}
