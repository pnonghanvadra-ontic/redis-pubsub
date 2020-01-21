package com.example.redispubsub;
import com.example.redispubsub.caches.Cache1;
import com.example.redispubsub.caches.Cache2;
import com.example.redispubsub.caches.Cache3;
import com.example.redispubsub.services.RedisService;
import com.example.redispubsub.services.RedisServiceImpl;
import com.example.redispubsub.user.UserService;
import com.example.redispubsub.user.UserServiceImpl;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

//    @Bean(name = "config")
//    public Config getConfig(){
//        Config config = new Config();
//        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
//        return config;
//    }

    @Bean(name = "cache1")
    Cache1 getCache1(UserService userService){
        return new Cache1(userService);
    }

    @Bean(name = "cache2")
    Cache2 getCache2(UserService userService){
        return new Cache2(userService);
    }

    @Bean(name = "cache3")
    Cache3 getCache3(UserService userService){
        return new Cache3(userService);
    }

    @Bean
    UserService getUserService(RedisService redisService){
        return new UserServiceImpl(redisService);
    }

    @Bean
    RedissonClient getRedissonClent() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }

    @Bean(name = "redisservice")
    public RedisService getRedisService(RedissonClient redissonClient){
        return new RedisServiceImpl(redissonClient);
    }

}
