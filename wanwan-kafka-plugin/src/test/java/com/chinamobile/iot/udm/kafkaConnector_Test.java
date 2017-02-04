package com.chinamobile.iot.udm;

import com.chinamobile.iot.udm.plugin.kafka.KafkaConnector;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhaofeng on 2016/12/12.
 */

public class kafkaConnector_Test {
    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:context.xml");
        KafkaConnector kafkaConnector = applicationContext.getBean(KafkaConnector.class);
       /* UdmOrderRequest udmOrderRequest = new UdmOrderRequest();
        Header header = new Header();
        header.setApplicationId("11");
        header.setMonitorKey("1");
        header.setOriginHost("1");
        header.setEnableMonitor(true);
        List<CharSequence> operseqs = new ArrayList();
        operseqs.add("1");
        udmOrderRequest.setHeader(header);
        udmOrderRequest.setQueryOprSeq(operseqs);
        udmOrderRequest.setProvinceID("11");*/
        kafkaConnector.send("AAA".getBytes());
    }
}
