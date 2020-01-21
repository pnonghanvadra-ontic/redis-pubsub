package com.example.redispubsub.user;

import com.example.redispubsub.services.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private RedisService redisService;
    // to be added ->  Map<String, User> userCache =

    @Autowired
    public UserServiceImpl(RedisService redisService) {
        this.redisService = redisService;
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
    public void publish(String msg) {
        redisService.publish(msg);
    }

    @Override
    public Object getClient() {
        return redisService.getClient();
    }

    /*public void lister(String listenedMessage) {
        String msg = listenedMessage;

        userCache.remove(msg);
    }*/
}
