package com.ibao.service.base;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ibao.model.Style;

@Repository
@Transactional(propagation=Propagation.NOT_SUPPORTED)
public class StyleService extends BasicService<Style>{
	
	public StyleService() {
		setClazz(Style.class);
	}
	
	@SuppressWarnings("unchecked")
	public Style selectStyleByCode(String code){
		String sql = "select a.* from style a where a.code = ?1";
		Query q = entityManager.createNativeQuery(sql, Style.class);
		q.setParameter(1, code);
		List<Style> list = q.getResultList();
		if(null != list && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Style> selectStyleBySeries(Integer series){
		String sql = "select a.* from style a where a.series = ?1";
		Query q = entityManager.createNativeQuery(sql, Style.class);
		q.setParameter(1, series);
		return q.getResultList();
	}
	
}
