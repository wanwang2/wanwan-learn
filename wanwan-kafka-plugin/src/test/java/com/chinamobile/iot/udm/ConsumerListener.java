package com.chinamobile.iot.udm;

import org.springframework.kafka.listener.MessageListener;

/**
 * Created by zhaofeng on 2016/12/12.
 */
public class ConsumerListener implements MessageListener {

    public void onMessage(Object o) {
        System.out.println(o);
    }
}
