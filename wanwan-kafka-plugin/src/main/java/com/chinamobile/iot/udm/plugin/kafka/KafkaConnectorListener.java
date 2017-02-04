package com.chinamobile.iot.udm.plugin.kafka;

/**
 * Created by zhaofeng on 2016/12/9.
 */
public interface KafkaConnectorListener {

    void beforeSend(KafkaConnectorEvent e);

    void afterSend(KafkaConnectorEvent e);

    void afterCommit(KafkaConnectorEvent e);

    void setKafkaConnector(KafkaConnector kafkaConnector);
}
