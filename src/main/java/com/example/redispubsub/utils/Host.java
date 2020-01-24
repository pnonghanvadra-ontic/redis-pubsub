package com.example.redispubsub.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public class Host {
    private String host;
    private int port;

    public Host() {
    }

    public Host(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @JsonIgnore
    public String getHostPort() {
        return this.host + ":" + this.port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Host host1 = (Host) o;
        return getPort() == host1.getPort() &&
                Objects.equals(getHost(), host1.getHost());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHost(), getPort());
    }

    @Override
    public String toString() {
        return "Host{" +
                "host='" + host + '\'' +
                ", port=" + port +
                '}';
    }
}
