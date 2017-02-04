package com.chinamobile.iot.udm.plugin.kafka;

import org.apache.avro.specific.SpecificRecordBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.ProducerListenerAdapter;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhaofeng on 2016/12/9.
 */
public class KafkaConnector implements InitializingBean, DisposableBean {
    private final static Logger log = LoggerFactory.getLogger(KafkaConnector.class);

    private KafkaTemplate           kafkaTemplate;
    private String                  topicName;
    private String                  messageKey;
    private int                     partionNum;
    private KafkaConnectorListener  kafkaConnectorListener;
    private ProducerListenerAdapter producerListenerAdapter;

    private String threadName = "KafkaConnector";
    private int    retryTimes = 3;

    public void send(byte[] message) throws KafkaException {
        if (kafkaConnectorListener != null) {
            kafkaConnectorListener.beforeSend(new KafkaConnectorEvent(threadName, KafkaConnectorEventType.SEND, message));
        }

        doSend(message);

        if (kafkaConnectorListener != null) {
            kafkaConnectorListener.afterSend(new KafkaConnectorEvent(threadName, KafkaConnectorEventType.SEND, message));
        }
    }

    protected void doSend(byte[] message) throws KafkaException {
        while (retryTimes > 0) {
            try {
                kafkaTemplate.send(topicName, message);
                break;
            } catch (Exception e) {
                retryTimes--;
                if (retryTimes == 0) {
                    retryTimes = 3;
                    log.error("send failed after {} times retry and the exception is {}", retryTimes, e.getMessage());
                    throw new KafkaException(e, KafkaException.RETRY_ERROR, KafkaException.RETRY_ERROR_MESSAGE);
                } else {
                    try {
                        TimeUnit.SECONDS.sleep(3L);
                    } catch (InterruptedException e1) {
                        log.error("InterruptedException:{}",e1);
                        Thread.currentThread().interrupt();
                    }
                    log.warn("message send failed retrying...");
                }
            }
        }
    }

    public void send(SpecificRecordBase message) throws KafkaException {
        if (kafkaConnectorListener != null) {
            kafkaConnectorListener.beforeSend(new KafkaConnectorEvent(threadName, KafkaConnectorEventType.SEND, message));
        }

        doSend(message);

        if (kafkaConnectorListener != null) {
            kafkaConnectorListener.afterSend(new KafkaConnectorEvent(threadName, KafkaConnectorEventType.SEND, message));
        }
    }

    protected void doSend(SpecificRecordBase message) throws KafkaException {
        while (retryTimes > 0) {
            try {
                kafkaTemplate.send(topicName, message);
                break;
            } catch (Exception e) {
                retryTimes--;
                if (retryTimes == 0) {
                    retryTimes = 3;
                    log.error("send failed after {} times retry and the exception is {}", retryTimes, e.getMessage());
                    throw new KafkaException(e, KafkaException.RETRY_ERROR, KafkaException.RETRY_ERROR_MESSAGE);
                } else {
                    try {
                        TimeUnit.SECONDS.sleep(3L);
                    } catch (InterruptedException e1) {
                        log.error("InterruptedException:{}",e1);
                        Thread.currentThread().interrupt();
                    }
                    log.warn("message send failed retrying...");
                }
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (kafkaTemplate == null) {
            throw new IllegalArgumentException("kafkaTemplate must not be null");
        }

        if (topicName == null || "".equals(topicName)) {
            throw new IllegalArgumentException("topicName must not be null or empty");
        }

        kafkaTemplate.setProducerListener(producerListenerAdapter);
    }

    @Override
    public void destroy() throws Exception {
        if (kafkaConnectorListener != null) {
            kafkaConnectorListener.setKafkaConnector(null);
        }
        setKafkaTemplate(null);
    }


    public void setKafkaTemplate(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public void setProducerListenerAdapter(ProducerListenerAdapter producerListenerAdapter) {
        this.producerListenerAdapter = producerListenerAdapter;
    }

    public void setPartionNum(int partionNum) {
        this.partionNum = partionNum;
    }

    public void setKafkaConnectorListener(KafkaConnectorListener kafkaConnectorListener) {
        this.kafkaConnectorListener = kafkaConnectorListener;
    }

    public void setRetryTimes(int retryTimes) {
        this.retryTimes = retryTimes;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }


}
