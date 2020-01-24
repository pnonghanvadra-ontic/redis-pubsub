package com.example.redispubsub.pubsub;

import com.example.redispubsub.services.RedisService;
import org.redisson.api.listener.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PubSubServiceImpl implements PubSubService {
    private final RedisService redisService;
    private Map<String, List<Integer>> topicSubscriber;

    @Autowired
    public PubSubServiceImpl(RedisService redisService) {
        this.redisService = redisService;
        topicSubscriber = new HashMap<>();
    }

    @Override
    public void publish(String topic, PubSubMessage pubSubMessage) {
        this.redisService.getTopic(topic).publish(pubSubMessage);
    }

    @Override
    public void subscribe(String topic, PubSubListner listener) {
        int regId = this.redisService.getTopic(topic).addListener(PubSubMessage.class, new MessageListener<PubSubMessage>() {
            @Override
            public void onMessage(CharSequence channel, PubSubMessage msg) {
                listener.onMessage(msg);
            }
        });
        if (!topicSubscriber.containsKey(topic)) {
            List<Integer> listnerId = new ArrayList<>();
            listnerId.add(regId);
            topicSubscriber.put(topic, listnerId);
        } else {
            topicSubscriber.get(topic).add(regId);
        }
        System.out.println(regId);
    }

    @Override
    public void unsubscribe(String topic) {

    }
}
