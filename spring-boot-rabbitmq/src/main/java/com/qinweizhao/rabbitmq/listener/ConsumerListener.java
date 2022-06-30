package com.qinweizhao.rabbitmq.listener;

import com.qinweizhao.rabbitmq.config.MyMqConfig;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author qinweizhao
 * @since 2022/6/30
 */
@Service
@RabbitListener(queues = MyMqConfig.QWZ_QUEUE_NAME)
public class ConsumerListener {


    private static final Logger logger = LoggerFactory.getLogger(ConsumerListener.class);


    @RabbitHandler
    public void listener(String msg, Channel channel, Message message) {
        logger.info(msg);

        try {
            // int i = 1/0;
            // 确认收到消息
            // 肯定确认；broker将移除此消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            System.out.println("消费者确认收到消息：" + msg);
        } catch (Exception e) {
            try {
                // 拒绝消息
                // 否定确认；可以指定broker是否丢弃此消息，可以批量
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
                // 同上，但不能批量
                // channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);
                System.out.println("消费者拒绝消息：" + msg);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

    }

}
