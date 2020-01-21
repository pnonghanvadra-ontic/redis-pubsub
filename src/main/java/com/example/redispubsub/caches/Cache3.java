package com.example.redispubsub.caches;

import com.example.redispubsub.services.RedisService;
import com.example.redispubsub.user.User;
import com.example.redispubsub.user.UserService;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.api.listener.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class Cache3 {
    private Map<String, User>  localCache;
    private UserService userService;

    @Autowired
    public Cache3(UserService userService) {
        this.localCache = new HashMap<>();
        this.userService = userService;
    }

    public void clearLocalCache(){
        localCache.clear();
    }

    public void updateLocalCache(){
        clearLocalCache();
        this.localCache = userService.getAll();
    }

    public void displayLocalCache(){
        for (Map.Entry<String,User> entry : localCache.entrySet())
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
        System.out.println("\n");
    }

    public void listen(String topic) {
        RedissonClient redissonClient = (RedissonClient) userService.getClient();
        RTopic rTopic = redissonClient.getTopic(topic);
        rTopic.addListener(String.class, new MessageListener<String>() {
            @Override
            public void onMessage(CharSequence channel,  String message) {
                System.out.println("Cache3: " + message);
                updateLocalCache();
                displayLocalCache();
            }
        });
    }
}
