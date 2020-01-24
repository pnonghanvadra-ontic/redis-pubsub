package com.example.redispubsub.factory;

import org.redisson.api.RedissonClient;

public interface RedisClientFactory {
    RedissonClient getClient();
}
