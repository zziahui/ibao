package com.ibao.service.base;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.NOT_SUPPORTED)
public class BasicService <T extends Serializable>{
	
	private Class<T> clazz;

    @PersistenceContext
    protected EntityManager entityManager;
    
    public final void setClazz(final Class<T> clazz) {
        this.clazz = clazz;
    }
    
    public T findOne(final Integer id) {
        return entityManager.find(clazz, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return entityManager.createQuery("from " + clazz.getName()).getResultList();
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public void create(final T entity) {
        entityManager.persist(entity);
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public T update(final T entity) {
        return entityManager.merge(entity);
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public void delete(final T entity) {
        entityManager.remove(entity);
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public void deleteById(final Integer entityId) {
        final T entity = findOne(entityId);
        delete(entity);
    }
    
    @SuppressWarnings("unchecked")
	public T findMaxId(){
    	String sql = "select a from "+clazz.getName() + " a order by id desc"; 
    	List<T> ts = entityManager.createQuery(sql).getResultList();
    	if(ts.size() > 0){
    		return ts.get(0);
    	}
    	return null;
    }
}
