package com.example.redispubsub.user;

import java.util.Map;

public interface UserService {
    User put(User user);
    User get(String id);
    User delete(String id);
    Map<String,User> getAll();
    void publish(String topic, Object msg);

}
