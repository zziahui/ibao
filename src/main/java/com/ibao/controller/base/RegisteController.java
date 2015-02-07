package com.ibao.controller.base;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ibao.model.User;
import com.ibao.service.base.UserService;

@Controller
public class RegisteController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/register.html")
	public String index(){
		return "register";
	}
	
	@RequestMapping(value="/signup.html", method=RequestMethod.POST)
	public ModelAndView signup(HttpServletRequest request){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		String email = request.getParameter("email");
		
		ModelAndView modelAndView = new ModelAndView();
		if(StringUtils.isBlank(username)){
			modelAndView.addObject("error", "用户名不能为空");
			modelAndView.setViewName("register");
			return modelAndView;
		}
		if(StringUtils.isBlank(password)){
			modelAndView.addObject("error", "密码不能为空");
			modelAndView.setViewName("register");
			return modelAndView;
		}
		if(StringUtils.isBlank(repassword)){
			modelAndView.addObject("error", "重复密码不能为空");
			modelAndView.setViewName("register");
			return modelAndView;
		}
		if(StringUtils.isBlank(email)){
			modelAndView.addObject("error", "邮箱不能为空");
			modelAndView.setViewName("register");
			return modelAndView;
		}
		User user = userService.selectUserByUserName(username);
		if(user != null){
			modelAndView.addObject("error", "用户已存在，请更换其他的用户名");
			modelAndView.setViewName("register");
			return modelAndView;
		}else{
			user = new User();
			user.setAddTime(new Date());
			user.setUsername(username);
			user.setEmail(email);
			user.setPassword(MD5Util.MD5(password));
			user.setRole("user");
			user.setRetry(0);
			userService.create(user);
			modelAndView.setViewName("redirect:/login.html");
			return modelAndView;
		}
	}
	
	@RequestMapping(value="/signup.html", method=RequestMethod.GET)
	public String sign(HttpServletRequest request){
		return "register";
	}

}
