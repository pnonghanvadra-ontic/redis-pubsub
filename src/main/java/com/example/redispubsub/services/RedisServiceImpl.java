package com.example.redispubsub.services;

import com.example.redispubsub.factory.RedisClientFactory;
import org.redisson.api.RKeys;
import org.redisson.api.RTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RedisServiceImpl implements RedisService {

//    private RedissonClient redissonClient;
    private RedisClientFactory redisClientFactory;

    @Autowired
    public RedisServiceImpl(RedisClientFactory redisClientFactory) {
        try {
            this.redisClientFactory = redisClientFactory;
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public <T> T put(String key, T value){
        redisClientFactory.getClient().getBucket(key).set(value);
        return value;
    }

    @Override
    public <T> T get(String key) {
        return (T) redisClientFactory.getClient().getBucket(key).get();
    }

    @Override
    public <T> T delete(String key) {
        T obj = (T) redisClientFactory.getClient().getBucket(key).get();
        redisClientFactory.getClient().getBucket(key).delete();
        return obj;
    }


    @Override
    public RTopic getTopic(String topic){
        RTopic rTopic = this.redisClientFactory.getClient().getTopic(topic);
        return rTopic;
    }

    @Override
    public void shutdown() {
        this.redisClientFactory.getClient().shutdown();
    }

    @Override
    public <T> Map<String, T> getAll() {
        RKeys rKeys = redisClientFactory.getClient().getKeys();
        Iterable<String> allKeys = rKeys.getKeys();
        Map<String, T> map = new HashMap<>();
        for(String key:allKeys){
            map.put(key, (T) get(key));
        }
        return map;
    }
}
