package com.okflow.middleware.activitymq;

import java.io.Serializable;

import java.util.Map;
import java.util.Map.Entry;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

/**
 * ActiveMQ生产者服务类
 * 
 * @author xiaofanglin
 * @version 2019-11-05
 * 
 */
/**
 
@Service
public class ProducerService {

	@Autowired
	private JmsTemplate jmsTemplate;

	public void sendTextMessage(Destination destination, final String msg) {
		if (null == destination) {
			destination = jmsTemplate.getDefaultDestination();
		}
		jmsTemplate.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session.createTextMessage(msg);
				return textMessage;
			}
		});
	}

	public void sendMapMessage(Destination destination, final Map<String, Object> map) {
		if (null == destination) {
			destination = jmsTemplate.getDefaultDestination();
		}
		jmsTemplate.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				MapMessage mapMessage = session.createMapMessage();
				for (Entry<String, Object> entry : map.entrySet()) {
					mapMessage.setObject(entry.getKey(), entry.getValue());
				}
				return mapMessage;
			}
		});
	}

	public void sendObjectMessage(Destination destination, final Serializable obj) {
		if (null == destination) {
			destination = jmsTemplate.getDefaultDestination();
		}
		jmsTemplate.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage objectMessage = session.createObjectMessage(obj);
				return objectMessage;
			}
		});
	}
}*/
