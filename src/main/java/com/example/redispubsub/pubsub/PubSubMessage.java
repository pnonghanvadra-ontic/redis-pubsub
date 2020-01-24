package com.example.redispubsub.pubsub;

import java.io.Serializable;

public class PubSubMessage implements Serializable {
    private Long NodeID;
    private Object message;

    public Long getNodeID() {
        return NodeID;
    }

    public void setNodeID(Long nodeID) {
        NodeID = nodeID;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
}
