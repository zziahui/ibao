package com.ibao.service.base;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ibao.model.Account;

@Service
public class AccountService extends BasicService<Account>{
	
	@Autowired
	private UserService userService;
	
	public AccountService() {
		setClazz(Account.class);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Account> selectAccountBySeries(Integer series){
		String sql = "select a.* from account a where a.series = ?1";
		Query q = entityManager.createNativeQuery(sql, Account.class);
		q.setParameter(1, series);
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public Account selectAccountByCodeAndUser(Integer user, String code){
		String sql = "select a.* from account a where a.user = ?1 and a.code = ?2";
		Query q = entityManager.createNativeQuery(sql, Account.class);
		q.setParameter(1, user);
		q.setParameter(2, code);
		List<Account> accounts = q.getResultList();
		if(accounts != null && accounts.size() > 0){
			return accounts.get(0);
		}
		return null;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void addAccount(Integer user, Integer num, String code, String admin){
		Account account = selectAccountByCodeAndUser(user, code);
		if(null == account){
			account = new Account();
			String[] clazzs = code.split("_");
			account.setGroup(Integer.valueOf(clazzs[0]));
			account.setClazz1(Integer.valueOf(clazzs[1]));
			account.setClazz2(Integer.valueOf(clazzs[2]));
			account.setCode(code);
			account.setTotal(num);
			account.setUser(userService.findOne(user));
			account.setCreatedDate(new Date());
			account.setCreateBy(userService.selectUserByUserName(admin));
			create(account);
		}else{
			Integer total = account.getTotal()+num;
			account.setTotal(total);
			account.setUpdateBy(userService.selectUserByUserName(admin));
			account.setUpdatedDate(new Date());
			update(account);
		}
	}
}
