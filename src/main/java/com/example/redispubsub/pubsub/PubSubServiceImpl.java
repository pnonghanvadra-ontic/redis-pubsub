package com.example.redispubsub.pubsub;

import com.example.redispubsub.services.RedisService;
import org.redisson.api.RTopic;
import org.redisson.api.listener.MessageListener;
import org.redisson.client.protocol.pubsub.PubSubMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PubSubServiceImpl implements PubSubService {

    private final RedisService redisService;

    @Autowired
    public PubSubServiceImpl(RedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public <T> void publish(String topic, T message) {
        this.redisService.getTopic(topic).publish(message);
    }

    @Override
    public <T> void subscribe(String topic, PubSubListner listener) {
        getTopic(topic).addListener(Object.class, new MessageListener<Object>() {
            @Override
            public void onMessage(CharSequence channel,  Object msg) {
                listener.onMessage(msg);
            }
        });
    }

    @Override
    public <T> void unsubscribe(String topic) {

    }

//    @Override
//    public <T> void unsubscribe(String topic) {
//        getTopic(topic).removeListener();
//    }

    @Override
    public RTopic getTopic(String topic) {
       return this.redisService.getTopic(topic);
    }
}
