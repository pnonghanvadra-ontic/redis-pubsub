package com.example.redispubsub.services;

import org.redisson.api.RedissonClient;

import java.util.Map;

public interface RedisService {
    <T> T put(String key, T value);
    <T> T get(String key);
    <T> T delete(String key);
    <T> void publish(T message);
    Object getClient();
    <T> Map<String, T> getAll();
//    <T> void listen(String topic) throws InterruptedException;
}

