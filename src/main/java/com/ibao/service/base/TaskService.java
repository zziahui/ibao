package com.ibao.service.base;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ibao.base.util.MyConstant;
import com.ibao.model.Account;
import com.ibao.model.Task;
import com.ibao.model.TaskDetail;

@Service
@Transactional(propagation=Propagation.NOT_SUPPORTED)
public class TaskService extends BasicService<Task>{
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private TaskDetailService taskDetailService;
	
	public TaskService() {
		setClazz(Task.class);
	}
	
	//获取一月内未完成的任务
	@SuppressWarnings("unchecked")
	public List<Task> selectTaskWithStateAndUser(Integer user){
		String sql = "select a.* from task a where a.user = ?1 and a.state <> 2 order by a.addTime desc LIMIT 20";
		Query q = entityManager.createNativeQuery(sql, Task.class);
		q.setParameter(1, user);
		return q.getResultList();
	}
	
	//任务开始
	//账户扣量
	//更新任务状态
	//添加任务账户状态记录
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean add(Task task, Account account){
		try{
			Integer total = task.getTotal();//代刷量
			Integer num = account.getTotal();//账户余额
			account.setTotal(num - total);
			account.setUpdatedDate(new Date());
			account.setUpdateBy(task.getUser());
			accountService.update(account);
			
			task.setState(1);
			update(task);
			
			TaskDetail td = new TaskDetail();
			td.setTask(task);
			td.setAccount(account);
			td.setLocked(total);
			//td.setAddTime(new Date());
			taskDetailService.create(td);
			MyConstant.tasks.add(task);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Task> selectTaskByState(int state){
		String sql = "select a.* from task a where a.state = ?1";
		Query q = entityManager.createNativeQuery(sql, Task.class);
		q.setParameter(1, state);
		return q.getResultList();
	}
	
	
	
}
