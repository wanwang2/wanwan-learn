package org.wanwanframework.activemq;

import com.chinamobile.iot.udm.jms.JMSConnector;

public class MQSender {

	private JMSConnector jmsConnector;
	private String message;
	
	public void send() {
		jmsConnector.send(message);
		System.out.println("message:" + message);
	}

	public JMSConnector getJmsConnector() {
		return jmsConnector;
	}

	public void setJmsConnector(JMSConnector jmsConnector) {
		this.jmsConnector = jmsConnector;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
