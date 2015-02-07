package com.ibao.rabbitmq;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMq {
	
	private Channel channel;
    private Connection connection;
	private String EXCHANGE_NAME;
	
	public RabbitMq(String EXCHANGE_NAME) {
		this.EXCHANGE_NAME = EXCHANGE_NAME;
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.1.11");
		factory.setPort(5672);
		factory.setUsername("guest");
		factory.setPassword("guest");
		try {
			this.connection = factory.newConnection();
			this.channel = connection.createChannel();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * 关闭channel和connection。并非必须，因为隐含是自动调用的。
     * @throws IOException
     */
     public void close() throws IOException{
         this.channel.close();
         this.connection.close();
     }
     
     
     public void sendMessage(String json){
    	 try {
			this.channel.basicPublish(getEXCHANGE_NAME(), "", null, json.getBytes());
			close();
    	 } catch (IOException e) {
			e.printStackTrace();
		}
     }

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public String getEXCHANGE_NAME() {
		return EXCHANGE_NAME;
	}

	public void setEXCHANGE_NAME(String eXCHANGE_NAME) {
		EXCHANGE_NAME = eXCHANGE_NAME;
	}
}
