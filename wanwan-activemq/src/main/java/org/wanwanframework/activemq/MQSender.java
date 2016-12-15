package org.wanwanframework.activemq;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

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
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext factory= new  ClassPathXmlApplicationContext("classpath:spring/context.xml"); 
		MQSender mqSender = (MQSender) factory.getBean("mqSender");
		mqSender.send();
		
	}
}
