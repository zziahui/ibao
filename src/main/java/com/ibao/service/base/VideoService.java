package com.ibao.service.base;

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

}
