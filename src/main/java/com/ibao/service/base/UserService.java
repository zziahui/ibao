package com.ibao.service.base;


import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ibao.model.User;

@Repository
@Transactional(propagation=Propagation.NOT_SUPPORTED)
public class UserService extends BasicService<User>{
	
	public UserService() {
		setClazz(User.class);
	}
	
	@SuppressWarnings("unchecked")
	public User selectUserByUserName(String username){
		String sql = "select a.* from user a where a.username = ?1";
		Query query = entityManager.createNativeQuery(sql, User.class);
		query.setParameter(1, username);
		List<User> users = query.getResultList();
		if(users != null && users.size() > 0){
			return users.get(0);
		}
		return null;
	}
	
}
