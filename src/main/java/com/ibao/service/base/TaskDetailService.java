package com.ibao.service.base;

import org.springframework.stereotype.Service;

import com.ibao.model.TaskDetail;

@Service
public class TaskDetailService extends BasicService<TaskDetail>{
	
	public TaskDetailService() {
		setClazz(TaskDetail.class);
	}

}
