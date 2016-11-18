package org.wanwanframework.activemq;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class MQReceiver implements Processor{

	private int count = 0;
	
	public void process(Exchange exchange) throws Exception {
		String messge = exchange.getIn().getBody().toString();
		System.out.println("exchange.getIn().getBody():" + messge);
		count++;
		System.out.println("count:" + count);
	}

}
