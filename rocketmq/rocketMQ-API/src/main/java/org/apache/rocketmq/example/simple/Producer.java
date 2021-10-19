package org.apache.rocketmq.example.simple;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.impl.producer.DefaultMQProducerImpl;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * 同步发送demo
 * */
public class Producer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer("group");
        producer.setNamesrvAddr("139.224.69.75:9876");
        producer.start();
        for (int i=0;i<20;i++){
            try {
                Message message = new Message("TopicTest","TagA","orderID188","Hello World".getBytes(RemotingHelper.DEFAULT_CHARSET));
                //同步发送
                SendResult result = producer.send(message);
                //异步发送
                producer.send(message, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        System.out.println("成功了！");
                    }

                    @Override
                    public void onException(Throwable e) {
                        System.out.println("失败了！");
                    }
                });
                System.out.println("同步发送返回："+JSON.toJSONString(result));
                //单向发送
//            producer.sendOneway(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
