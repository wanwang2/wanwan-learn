package org.wanwanframework.activemq;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class MQReceiver implements Processor{

	public void process(Exchange exchange) throws Exception {
		String messge = exchange.getIn().toString();
		System.out.println("message:" + messge);
		
	}

}
