package com.okflow.middleware.activitymq;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

/**
 * 消费者服务类
 * 
 * @author xiaofanglin
 * @version 2019-11-05
 */
public class QueueMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) message;
			try {
				System.out.println("textMessage=" + textMessage.getText());
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (message instanceof MapMessage) {
			MapMessage mapMessage = (MapMessage) message;
			System.out.println("mapMessage=" + mapMessage);

		}

		if (message instanceof ObjectMessage) {
			ObjectMessage objectMessage = (ObjectMessage) message;
			// ExampleUser exampleUser = (ExampleUser) om.getObject();
			System.out.println("objectMessage=" + objectMessage);

		}

	}

}
