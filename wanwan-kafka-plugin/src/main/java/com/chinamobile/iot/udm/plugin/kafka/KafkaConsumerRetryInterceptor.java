package com.chinamobile.iot.udm.plugin.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;

/**
 * Created by zhaofeng on 2016/12/14.
 */
public class KafkaConsumerRetryInterceptor implements RetryListener {
    private final static Logger logger = LoggerFactory.getLogger(KafkaMessageListener.class);

    @Override
    public <T, E extends Throwable> boolean open(RetryContext retryContext, RetryCallback<T, E> retryCallback) {
        return true;
    }

    @Override
    public <T, E extends Throwable> void close(RetryContext retryContext, RetryCallback<T, E> retryCallback, Throwable throwable) {
        if (throwable != null) {
            logger.error("consuming message:{} failed after {} times retry and the error info is: {}",
                    retryContext.getAttribute("record"),
                    retryContext.getRetryCount() + 1,
                    throwable.getMessage());
        }
    }

    @Override
    public <T, E extends Throwable> void onError(RetryContext retryContext, RetryCallback<T, E> retryCallback, Throwable throwable) {
        logger.error("error:{} occured when consuming message:{} retrying for the {} time",
                throwable.getMessage(),
                retryContext.getAttribute("record"),
                retryContext.getRetryCount() + 1);
    }
}
