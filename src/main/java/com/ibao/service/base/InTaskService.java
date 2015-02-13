package com.ibao.service.base;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ibao.model.InTask;

@Service
public class InTaskService extends BasicService<InTask>{

	public InTaskService() {
		setClazz(InTask.class);
	}
	
	@SuppressWarnings("unchecked")
	public int getMaxSort(String server){
		String sql = "select count(*) from intask where server = ?1";
		Query q = entityManager.createNativeQuery(sql);
		q.setParameter(1, server);
		List list = q.getResultList();
		if(list != null && list.size() > 0)
			return Integer.valueOf(list.get(0).toString());
		else
			return 0;
		
	}
	
	//1 队列中 2执行中 3执行结束
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveInTask(InTask inTask){
		int maxCount = getMaxSort(inTask.getServer())+1;
		inTask.setSort(maxCount);
		create(inTask);
	}
}
