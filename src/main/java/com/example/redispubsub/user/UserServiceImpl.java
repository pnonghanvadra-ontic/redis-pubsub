package com.example.redispubsub.user;

import com.example.redispubsub.pubsub.PubSubMessage;
import com.example.redispubsub.pubsub.PubSubService;
import com.example.redispubsub.services.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private RedisService redisService;
    private PubSubService pubSubService;
    // to be added ->  Map<String, User> userCache =

    @Autowired
    public UserServiceImpl(RedisService redisService, PubSubService pubSubService) {
        this.redisService = redisService;
        this.pubSubService = pubSubService;
    }

    @Override
    public User put(User user) {
        // to be added -> mongo.save(user);
        // to be added -> userCache.put(user.getId(), user);
        // to be added -> return redisService.publish(user.getId());
        redisService.put(user.getId(), user);
        return (User)redisService.get(user.getId());
    }

    @Override
    public User get(String id) {
        return (User)redisService.get(id);
    }

    @Override
    public User delete(String id) {
        User user = (User)redisService.get(id);
        redisService.delete(id);
        return user;
    }

    @Override
    public Map<String, User> getAll() {
        return redisService.getAll();
    }

    @Override
    public void publish(String topic, PubSubMessage message) {
        pubSubService.publish(topic, message);
    }

//    @Override
//    public RTopic getTopic(String topic){
//        return pubSubService.getTopic(topic);
//    }

    /*public void lister(String listenedMessage) {
        String msg = listenedMessage;

        userCache.remove(msg);
    }*/
}
