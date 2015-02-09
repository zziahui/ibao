package com.ibao.task;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

import com.ibao.base.util.MyConstant;
import com.ibao.base.util.VideoUtil;
import com.ibao.model.Task;
import com.ibao.model.User;
import com.ibao.model.Video;
import com.ibao.rabbitmq.RabbitMq;
import com.ibao.service.base.TaskService;
import com.ibao.service.base.VideoService;

public class InitThread extends Thread{
	
	private Log log = LogFactory.getLog(InitThread.class);
	
	private TaskService taskService;
	
	private RabbitMq mq;
	
	private VideoService videoService;
	
	private CountDownLatch countDownLatch = new CountDownLatch(1);
	
	public InitThread(RabbitMq mq, TaskService taskService, VideoService videoService){
		this.mq = mq;
		this.taskService = taskService;
		this.videoService = videoService;
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
					Video video = videoService.selectByUrl(task.getUrl());
					if(null != video){
						JSONObject jsonObject = new JSONObject(video);
						jsonObject.put("code", task.getStyle().getCode());
						jsonObject.put("task", task.getId());
						this.mq.sendMessage(jsonObject.toString());
					}
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
	
	public static void main(String[] args) {
		User user = new User();
		user.setUsername("hello");
		JSONObject jsonObject = new JSONObject(user);
		jsonObject.put("code", "111");
		System.out.println(jsonObject);
	}
}
