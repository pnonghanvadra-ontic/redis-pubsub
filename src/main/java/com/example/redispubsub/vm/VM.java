package com.example.redispubsub.vm;

import com.example.redispubsub.pubsub.PubSubListner;
import com.example.redispubsub.pubsub.PubSubService;
import com.example.redispubsub.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class VM implements PubSubListner {
    private static final Long id = 1L;
    private Map<String, User>  localCache;
    private PubSubService pubSubService;

    @Autowired
    public VM(PubSubService pubSubService) {
        this.localCache = new HashMap<>();
        this.pubSubService = pubSubService;
    }

    public void displayLocalCache(){
        for (Map.Entry<String,User> entry : localCache.entrySet())
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
        System.out.println("\n");
    }

    public void subscribe(String topic) {
        pubSubService.subscribe(topic,this);
    }

    public void unsubscribe(String topic){
        pubSubService.unsubscribe(topic);
    }

    public User find(String key){
        return localCache.get(key);
    }

    public <T> void add(T object){
        User user = (User)object;
        localCache.put(user.getId(),user);
    }

    public User delete(String key){
        User user = localCache.get(key);

        // PubSubMessage can be added 

        pubSubService.publish("ontic",user);
        localCache.remove(key);
        return user;
    }

    @Override
    public <T> void onMessage(T message) {
        System.out.println("Message recieved");
        System.out.println(message);
    }
}
