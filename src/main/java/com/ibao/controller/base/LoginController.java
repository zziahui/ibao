package com.ibao.controller.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ibao.service.base.BaseService;

@Controller
public class LoginController {
	
	@Autowired
	private BaseService baseService;
	
	@RequestMapping(value={"/login", "/login.html", "/login.do"})
	public String login(HttpServletRequest request){
		boolean isLogin = baseService.isLogin(request);
		if(isLogin)
			return "redirect:/index";
		return "login";
	}

}
