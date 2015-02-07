package com.ibao.service.base;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ibao.model.Code;

@Repository
@Transactional(propagation=Propagation.NOT_SUPPORTED)
public class CodeService extends BasicService<Code>{
	public CodeService() {
		setClazz(Code.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Code> selectCodesByType(Integer type){
		String sql = "select a.* from code a where a.type = ?1";
		Query q = entityManager.createNativeQuery(sql, Code.class);
		q.setParameter(1, type);
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public Code selectCodesByTypeAndCode(Integer type, Integer code){
		String sql = "select a.* from code a where a.type = ?1 and a.code = ?2";
		Query q = entityManager.createNativeQuery(sql, Code.class);
		q.setParameter(1, type);
		q.setParameter(2, code);
		List<Code> codes = q.getResultList();
		if(codes != null && codes.size() > 0 ){
			return codes.get(0);
		}
		return null;
	}

}
