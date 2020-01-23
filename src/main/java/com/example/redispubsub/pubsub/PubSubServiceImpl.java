package com.example.redispubsub.pubsub;

import com.example.redispubsub.services.RedisService;
import org.redisson.api.listener.MessageListener;
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
    public void publish(String topic, PubSubMessage pubSubMessage) {
        this.redisService.getTopic(topic).publish(pubSubMessage);
    }

    @Override
    public void subscribe(String topic, PubSubListner listener) {
        this.redisService.getTopic(topic).addListener(PubSubMessage.class, new MessageListener<PubSubMessage>() {
            @Override
            public void onMessage(CharSequence channel,  PubSubMessage msg) {
                listener.onMessage(msg);
            }
        });
    }

    @Override
    public void unsubscribe(String topic) {

    }
}
