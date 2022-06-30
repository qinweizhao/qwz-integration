package com.qinweizhao.rabbitmq.controller;

import com.qinweizhao.rabbitmq.config.MyMqConfig;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author qinweizhao
 * @since 2022/6/30
 */
@RestController
public class MqController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public String sendMsg(){
        String msg = "这是一个消息";
        rabbitTemplate.convertAndSend(MyMqConfig.QWZ_EXCHANGE_NAME,MyMqConfig.QWZ_ROUTING_KEY,msg,new CorrelationData(UUID.randomUUID().toString()));
        return "消息发送成功";
    }
}
