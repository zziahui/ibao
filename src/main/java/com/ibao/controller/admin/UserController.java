package com.ibao.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ibao.model.User;
import com.ibao.service.base.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value={"/admin/user.html", "/admin/user.do"})
	public ModelAndView index(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		List<User> users = userService.findAll();
		modelAndView.addObject("users", users);
		modelAndView.setViewName("admin/user/index");
		return modelAndView;
	}
	
	@RequestMapping(value="/admin/user/{id}")
	@ResponseBody
	public ModelMap show(@PathVariable Integer id){
		ModelMap modelMap = new ModelMap();
		User user = userService.findOne(id);
		modelMap.addAttribute("user", user);
		return modelMap;
	}
	

}
