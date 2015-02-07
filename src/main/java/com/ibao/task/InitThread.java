package com.ibao.task;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

import com.ibao.base.util.MyConstant;
import com.ibao.base.util.VideoUtil;
import com.ibao.model.Task;
import com.ibao.rabbitmq.RabbitMq;
import com.ibao.service.base.TaskService;

public class InitThread extends Thread{
	
	private Log log = LogFactory.getLog(InitThread.class);
	
	private TaskService taskService;
	
	private RabbitMq mq;
	
	private CountDownLatch countDownLatch = new CountDownLatch(1);
	
	public InitThread(RabbitMq mq, TaskService taskService){
		this.mq = mq;
		this.taskService = taskService;
	}

	@Override
	public void run() {
		long t1 = System.currentTimeMillis();
		try{
			for(Task task : MyConstant.tasks){
				Map<String, Integer> map = VideoUtil.checkVideo(task.getUrl());
				if(map.get("vv") > task.getEnd()){
					if(!MyConstant.endTasks.contains(task)){
						task.setState(2);
						this.taskService.update(task);
						MyConstant.endTasks.add(task);
					}
				}else{
					//this.mq.sendMessage(JSONObject.wrap(task).toString());
				}
			}
			for(Task task : MyConstant.endTasks){
				if(MyConstant.tasks.contains(task)){
					MyConstant.tasks.remove(task);
				}
			}
		}finally{
			
		}
		long t2 = System.currentTimeMillis() - t1;
		log.info("校验任务耗时"+t2);
		//LogTask.sendLog(t2);
		
	}

	public CountDownLatch getCountDownLatch() {
		return countDownLatch;
	}
}
