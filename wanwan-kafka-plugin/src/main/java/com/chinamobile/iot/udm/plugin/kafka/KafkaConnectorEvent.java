package com.chinamobile.iot.udm.plugin.kafka;

/**
 * Created by zhaofeng on 2016/12/9.
 */
public class KafkaConnectorEvent {
    public String                threadName;
    public KafkaConnectorEventType type;
    public Object                event;

    public KafkaConnectorEvent(String threadName, KafkaConnectorEventType type, Object event) {
        this.threadName = threadName;
        this.type = type;
        this.event = event;
    }

}
