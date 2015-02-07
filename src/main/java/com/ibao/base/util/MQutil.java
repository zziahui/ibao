package com.ibao.base.util;

import com.ibao.rabbitmq.RabbitMq;

public class MQutil {

	
	public static void main(String[] args) throws Exception {
		while(true){
			RabbitMq mq = new RabbitMq(MyConstant.EXCHANGE_TASK_NAME);
			mq.sendMessage(System.currentTimeMillis()+"");
			Thread.sleep(1000);
			mq.close();
		}
	}
}
