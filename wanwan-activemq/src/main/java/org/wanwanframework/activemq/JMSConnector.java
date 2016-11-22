package org.wanwanframework.activemq;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * Created by ZengGaohua on 2016-05-17.
 */
public class JMSConnector {

	private JmsTemplate jmsTemplate;
	private String destinationQueueName;

	public void send(final Object message) {
	
		jmsTemplate.send(destinationQueueName, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                if (message instanceof String) {
                    return session.createTextMessage((String) message);
                }
                if (message instanceof MapMessage) {
                    return (MapMessage) message;
                }
                return session.createObjectMessage((Serializable) message);
            }
        });
	}

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public String getDestinationQueueName() {
		return destinationQueueName;
	}

	public void setDestinationQueueName(String destinationQueueName) {
		this.destinationQueueName = destinationQueueName;
	}

}
