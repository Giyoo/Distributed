package org.apache.rocketmq.example.quickstart;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * @program: rabbitmq-base
 * @description: 发送者
 * @author: guoyong
 * @create: 2021-09-02 17:02
 **/
public class Producer {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("default_group");
        producer.setNamesrvAddr("139.224.69.75:9876");
        producer.start();
        for (int i=0;i<2;i++){
            try {
                Message msg = new Message("TopicTest","TagA", ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) );
                //messageDelayLevel=1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
                msg.setDelayTimeLevel(3);
                SendResult sendResult = producer.send(msg);
                System.out.printf("%s%n", sendResult);
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }
        producer.shutdown();
    }
}
