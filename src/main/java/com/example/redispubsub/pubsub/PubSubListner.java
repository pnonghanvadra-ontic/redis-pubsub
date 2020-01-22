package com.example.redispubsub.pubsub;

public interface PubSubListner {
    <T> void onMessage(T messege);
}
