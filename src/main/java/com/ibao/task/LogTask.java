package com.ibao.task;


import com.ibao.base.util.MyConstant;
import com.ibao.rabbitmq.RabbitMq;

public class LogTask {
	
	public static void sendLog(long consuming) {
		
		RabbitMq mq = new RabbitMq(MyConstant.EXCHANGE_LOG_NAME);
		mq.sendMessage("1_"+consuming);
	}

}
