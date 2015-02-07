package com.ibao.service.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

@Service
public class BaseService {
	
	public boolean isLogin(HttpServletRequest request){
		HttpSession session = request.getSession();
		if(session.getAttribute("user") != null)
			return  true;
		return false;
	}

}
