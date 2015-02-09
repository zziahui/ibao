package com.ibao.task;


//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.ibao.base.util.ContextUtil;
import com.ibao.base.util.MyConstant;
import com.ibao.rabbitmq.RabbitMq;
import com.ibao.service.base.TaskService;
import com.ibao.service.base.VideoService;

public class InstantiationTracingBeanPostProcessor implements ApplicationListener<ContextRefreshedEvent>{

	//private Log log = LogFactory.getLog(InstantiationTracingBeanPostProcessor.class);
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private VideoService videoService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getParent() == null){//root application context 没有parent，他就是老大.
			ContextUtil.getContext();
			MyConstant.tasks.addAll(taskService.selectTaskByState(1));//把所有未完成的任务加入
			RabbitMq mq = new RabbitMq(MyConstant.EXCHANGE_TASK_NAME);
			while(true){
				InitThread thread = new InitThread(mq, taskService, videoService);
				thread.start();
				try {
					Thread.sleep(10*1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
