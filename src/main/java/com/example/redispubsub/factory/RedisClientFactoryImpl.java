package com.example.redispubsub.factory;

import com.example.redispubsub.config.RedisConfig;
import com.example.redispubsub.utils.Host;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisClientFactoryImpl implements RedisClientFactory{
    private RedisConfig redisConfig;

    @Autowired
    public RedisClientFactoryImpl(RedisConfig redisConfig) {
        this.redisConfig = redisConfig;
    }

    public RedissonClient getClient(){
        Config config = new Config();
        if(redisConfig.isClusterMode()){
            List<Host> hostList = redisConfig.getHosts();
            String serverAddresses = getServerAddresses(hostList);
            config.useClusterServers().addNodeAddress(serverAddresses.split(","));
            return Redisson.create(config);
        }
        else {
            List<Host> hostList = redisConfig.getHosts();
            StringBuilder redisServerAddress = new StringBuilder();
            redisServerAddress.append("redis://")
                    .append(hostList.get(0).getHost())
                    .append(":")
                    .append(String.valueOf(hostList.get(0).getPort()));
            config.useSingleServer().setAddress(redisServerAddress.toString());
            return Redisson.create(config);
        }
    }

    String getFormattedRedisAddress(String host, String port){
        return "redis://" + host + ":" + port;
    }

    private String getServerAddresses(List<Host> hostList) {
        StringBuilder stringBuilder = new StringBuilder();
        for(Host hostInfo: hostList){
            stringBuilder.append(getFormattedRedisAddress(hostInfo.getHost(), String.valueOf(hostInfo.getPort())));
            stringBuilder.append(",");
        }
        String hosts = stringBuilder.toString();
        return hosts.substring(0,hosts.length()-1);
    }
}
