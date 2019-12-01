package com.okflow.middleware.activitymq;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import com.okflow.common.utils.SpringContextHolder;
import com.okflow.modules.received.service.ImessageService;

/**
 * 消费者服务类
 * 
 * @author xiaofanglin
 * @version 2019-11-05
 
public class QueueMessageListener implements MessageListener {

	private static ImessageService imessageService = SpringContextHolder.getBean(ImessageService.class);

	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) message;
			try {
				System.out.println("textMessage=" + textMessage.getText());
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}

		if (message instanceof MapMessage) {
			MapMessage mapMessage = (MapMessage) message;
			try {
				String producerKey = (String) mapMessage.getObject("producerKey");
				String theme = (String) mapMessage.getObject("theme");
				String content = (String) mapMessage.getObject("content");
				String rever = (String) mapMessage.getObject("rever");
				String consumerKey = (String) mapMessage.getObject("consumerKey");
				imessageService.saveImessage(producerKey, theme, content, rever, consumerKey);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		if (message instanceof ObjectMessage) {
			ObjectMessage objectMessage = (ObjectMessage) message;
			// ExampleUser exampleUser = (ExampleUser) om.getObject();
			System.out.println("objectMessage=" + objectMessage);

		}

	}

}*/
