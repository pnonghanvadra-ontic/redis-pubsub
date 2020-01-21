package com.example.redispubsub.services;

import org.redisson.api.RKeys;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.api.listener.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class RedisServiceImpl implements RedisService {

    private RedissonClient redissonClient;
    private RTopic rTopic;
    private static final String TOPIC = "ontic";


    @Autowired
    public RedisServiceImpl(RedissonClient redissonClient) {
        try {
            this.redissonClient = redissonClient;
            rTopic = redissonClient.getTopic(TOPIC);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public <T> T put(String key, T value){
        redissonClient.getBucket(key).set(value);
        return value;
    }

    @Override
    public <T> T get(String key) {
        return (T) redissonClient.getBucket(key).get();
    }

    @Override
    public <T> T delete(String key) {
        T obj = (T) redissonClient.getBucket(key).get();
        redissonClient.getBucket(key).delete();
        return obj;
    }

//    @Override
//    public <T> void listen(String topic) throws InterruptedException {
//        RTopic rTopic = redissonClient.getTopic(topic);
//        CountDownLatch latch = new CountDownLatch(2);
//        ((RTopic) rTopic).addListener(String.class, new MessageListener<String>() {
//            @Override
//            public void onMessage(CharSequence channel,  String message) {
//                System.out.println(message);
//                latch.countDown();
//            }
//        });
//        latch.await();
//    }

    @Override
    public Object getClient() {
        return redissonClient;
    }

    @Override
    public <T> Map<String, T> getAll() {
        RKeys rKeys = redissonClient.getKeys();
        Iterable<String> allKeys = rKeys.getKeys();
        Map<String, T> map = new HashMap<>();
        for(String key:allKeys){
            map.put(key, (T) get(key));
        }
        return map;
    }

    @Override
    public <T> void publish(T msg){
        rTopic.publish(msg);
    }
}
