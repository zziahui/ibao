package com.ibao.service.base;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ibao.model.Video;
@Service
@Transactional(propagation=Propagation.NOT_SUPPORTED)
public class VideoService extends BasicService<Video>{
	
	public VideoService() {
		setClazz(Video.class);
	}
	
	@SuppressWarnings("unchecked")
	public Video selectByUrl(String url){
		String sql = "select * from video where url = ?1";
		Query q = entityManager.createNativeQuery(sql, Video.class);
		q.setParameter(1, url);
		List<Video> videos = q.getResultList();
		if(null != videos && videos.size() > 0){
			return videos.get(0);
		}
		return null;
	}

}
