package org.wanwanframework.kafka;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.ProducerListenerAdapter;

/**
 * Created by zhaofeng on 2016/12/14.
 */
public class KafkaProducerListener<K, V> extends ProducerListenerAdapter<K, V> {
    private final static Logger log = LoggerFactory.getLogger(KafkaProducerListener.class);

    @Override
    public void onSuccess(String topic, Integer partition, K key, V value, RecordMetadata recordMetadata) {
        log.info("send message:{} to toppic:{} partition:{}  success", value, topic, partition);
    }

    @Override
    public void onError(String topic, Integer partition, K key, V value, Exception exception) {
        log.info("send message:{} to toppic:{} partition:{}  error and the exception info is:{}", value, topic, partition, exception.getMessage());
    }

    @Override
    public boolean isInterestedInSuccess() {
        return true;
    }
}
