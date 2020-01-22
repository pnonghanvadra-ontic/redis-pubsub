package com.example.redispubsub.services;

import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;

import java.util.Map;

public interface RedisService {
    <T> T put(String key, T value);
    <T> T get(String key);
    <T> T delete(String key);
    <T> Map<String, T> getAll();
    RTopic getTopic(String topic);
}

