package com.example.redispubsub.services;

import com.example.redispubsub.pubsub.PubSubMessage;
import org.redisson.api.RTopic;

import java.util.Map;

public interface RedisService {
    <T> T put(String key, T value);
    <T> T get(String key);
    <T> T delete(String key);
    <T> Map<String, T> getAll();
//    void publish(String topic, PubSubMessage pubSubMessage);
    RTopic getTopic(String topic);
    void shutdown();
}

