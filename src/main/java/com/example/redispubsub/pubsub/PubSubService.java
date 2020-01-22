package com.example.redispubsub.pubsub;

import org.redisson.api.RTopic;

public interface PubSubService {
    <T> void publish(String topic, T message);
    <T> void subscribe(String topic, final PubSubListner listener);
    <T> void unsubscribe(String topic);
    RTopic getTopic(String topic);
}
