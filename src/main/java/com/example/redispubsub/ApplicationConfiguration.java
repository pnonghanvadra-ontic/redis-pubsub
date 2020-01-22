package com.example.redispubsub;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration()
//@ComponentScan({"com.example.redispubsub"})
public class ApplicationConfiguration {

//    @Bean(name = "config")
//    public Config getConfig(){
//        Config config = new Config();
//        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
//        return config;
//    }

//    @Bean(name = "vm1")
//    VM1 getVM1(UserService userService){
//        return new VM1(userService);
//    }

//    @Bean(name = "cache2")
//    VM2 getCache2(UserService userService){
//        return new VM2(userService);
//    }
//
//    @Bean(name = "cache3")
//    VM3 getCache3(UserService userService){
//        return new VM3(userService);
//    }

//    @Bean
//    UserService getUserService(RedisService redisService, PubSubService pubSubService){
//        return new UserServiceImpl(redisService, pubSubService);
//    }
//
    @Bean
    RedissonClient getRedissonClent() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }
//
//    @Bean(name="pubSubService")
//    PubSubService getPusSubService(RedissonClient redissonClient){
//        return new PubSubServiceImpl(redissonClient);
//    }
//
//    @Bean(name = "redisservice")
//    public RedisService getRedisService(RedissonClient redissonClient){
//        return new RedisServiceImpl(redissonClient);
//    }

}
