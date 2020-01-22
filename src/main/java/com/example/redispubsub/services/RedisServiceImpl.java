package com.example.redispubsub.services;

import org.redisson.api.RKeys;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RedisServiceImpl implements RedisService {

    private RedissonClient redissonClient;

    @Autowired
    public RedisServiceImpl(RedissonClient redissonClient) {
        try {
            this.redissonClient = redissonClient;
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


    @Override
    public RTopic getTopic(String topic){
        RTopic rTopic = this.redissonClient.getTopic(topic);
        return rTopic;
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
}
