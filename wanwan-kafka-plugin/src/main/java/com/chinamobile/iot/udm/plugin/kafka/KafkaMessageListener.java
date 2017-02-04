package com.chinamobile.iot.udm.plugin.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.kafka.listener.MessageListener;

/**
 * Created by zhaofeng on 2016/12/9.
 */
public class KafkaMessageListener implements MessageListener, InitializingBean, DisposableBean {
    private final static Logger logger = LoggerFactory.getLogger(KafkaMessageListener.class);

    @Override
    public void onMessage(Object message) {
        if (null == message) {
            logger.error("receive message from kafka is null");
            return;
        }
        process(message);
    }

    public void process(Object message) {
    }

    @Override
    public void destroy() throws Exception {
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("{} is started", this.getClass().getSimpleName());
    }

}
