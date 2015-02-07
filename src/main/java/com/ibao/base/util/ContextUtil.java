package com.ibao.base.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ContextUtil {
	
	private static ApplicationContext context;
	
	static{
		String configLocation = "classpath*:config/spring-config.xml";
		context = new FileSystemXmlApplicationContext(configLocation);
	}
	
	
	public static ApplicationContext getContext(){
		return context;
	}
	
	public static <T> T getClassBean(Class<T> clazz){
		return context.getBean(clazz);
	}

}
