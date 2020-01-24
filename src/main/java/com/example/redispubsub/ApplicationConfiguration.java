package com.example.redispubsub;
import com.example.redispubsub.config.RedisConfig;
import com.example.redispubsub.utils.Host;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration()
public class ApplicationConfiguration {

//    @Bean
//    RedissonClient getRedissonClent() {
//        Config config = new Config();
//        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
//        RedissonClient redissonClient = Redisson.create(config);
//        return redissonClient;
//    }



    @Bean
    RedisConfig getRedisConfig(){
        RedisConfig redisConfig = new RedisConfig();
        Host host = new Host();
        host.setHost("127.0.0.1");
        host.setPort(6379);
        List<Host> hostList = new ArrayList<>();
        hostList.add(host);
        redisConfig.setHosts(hostList);
        return redisConfig;
    }
}
