package com.example.redispubsub.pubsub;

public interface PubSubService {
    void publish(String topic, PubSubMessage pubSubMessage);
    void subscribe(String topic, final PubSubListner listener);
    void unsubscribe(String topic);
}
