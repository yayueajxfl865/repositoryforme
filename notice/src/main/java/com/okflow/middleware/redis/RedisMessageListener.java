package com.okflow.middleware.redis;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
/**
 * 配置reids消息的监听器:获取redis中的信息并进行处理
 * @author  xiaofanling
 * @version 2019-10-02
 */
public class RedisMessageListener implements MessageListener {

	/**
	 *  onMessage:处理消息
	 *  onMessage:完整的消息(频道的信息,以及消息的具体内容)
	 *  pattern:获取的频道信息
	 */
	@Override
	public void onMessage(Message onMessage, byte[] pattern) {


	}

}
