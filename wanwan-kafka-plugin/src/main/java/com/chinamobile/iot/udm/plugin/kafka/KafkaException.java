package com.chinamobile.iot.udm.plugin.kafka;

/**
 * Created by zhaofeng on 2016/12/13.
 */
public class KafkaException extends Exception {
    public final static int    RETRY_ERROR         = 100;
    public static final String RETRY_ERROR_MESSAGE = "Retry failed Exception";
    public int    code;
    public String message;


    public KafkaException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public KafkaException(Throwable cause, int code, String message) {
        super(cause);
        this.code = code;
        this.message = message;
    }
}
