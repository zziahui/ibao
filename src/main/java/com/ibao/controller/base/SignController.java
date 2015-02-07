package com.ibao.controller.base;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ibao.model.User;
import com.ibao.service.base.BaseService;
import com.ibao.service.base.UserService;

@Controller
public class SignController {
	
	@Autowired
	private BaseService baseService;
	
	@Autowired
	private UserService userService;
	
	//登陆方法
	@RequestMapping(value="/signin.html", method=RequestMethod.POST)
	public ModelAndView signin(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		String username = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
		if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)){
			User user = userService.selectUserByUserName(username);
			if(user == null){
				modelAndView.addObject("error", "用户不存在,请重新填写");
				modelAndView.setViewName("login");
				return modelAndView;
			}
			if(!MD5Util.MD5(password).equals(user.getPassword())){
				modelAndView.addObject("error", "密码不正确,请重新填写");
				modelAndView.setViewName("login");
				return modelAndView;
			}
			if(user.getRetry() > 0){
				user.setRetry(0);
				user.setState("ok");
			}
			user.setSignTime(new Date());
			user.setSignIp(request.getRemoteAddr());
			userService.update(user);
			HttpSession session = request.getSession();
			session.setAttribute("user", username);
			modelAndView.setViewName("redirect:/index.html");
			return modelAndView;
		}else{
			modelAndView.setViewName("login");
			return modelAndView;
		}
	}
	//
	@RequestMapping(value="/signin", method=RequestMethod.GET)
	public String signin(){
		return "redirect:/login.html";
	}

}
