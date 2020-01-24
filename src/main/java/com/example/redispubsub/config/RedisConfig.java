package com.example.redispubsub.config;

import com.example.redispubsub.utils.Host;

import java.util.List;

// should extends ResourceConfig
public class RedisConfig  {

//    private static final long serialVersionUID = -5118971385993262808L;
    private List<Host> hosts;

    private boolean clusterMode;

    private Long timeout;

    private Long scanInterval = 1000L;

    private String clientType;

    public List<Host> getHosts() {
        return hosts;
    }

    public void setHosts(List<Host> hosts) {
        this.hosts = hosts;
    }

    public boolean isClusterMode() {
        return clusterMode;
    }

    public void setClusterMode(boolean clusterMode) {
        this.clusterMode = clusterMode;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    public Long getScanInterval() {
        return scanInterval;
    }

    public void setScanInterval(Long scanInterval) {
        this.scanInterval = scanInterval;
    }

    @Override
    public String toString() {
        return "RedisConfig{" +
                "hosts=" + hosts +
                ", clusterMode=" + clusterMode +
                ", timeout=" + timeout +
                ", scanInterval=" + scanInterval +
                ", clientType='" + clientType + '\'' +
                "} " + super.toString();
    }
}

