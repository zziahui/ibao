package com.ibao.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ibao.model.Account;
import com.ibao.model.Code;
import com.ibao.model.Style;
import com.ibao.model.User;
import com.ibao.service.base.AccountService;
import com.ibao.service.base.CodeService;
import com.ibao.service.base.StyleService;
import com.ibao.service.base.UserService;

@Controller
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private StyleService styleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CodeService codeService;
	
	@RequestMapping(value="/admin/account.html")
	public ModelAndView index(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/account/index");
		//1代表视频类
		List<Account> accounts = accountService.selectAccountBySeries(1);
		List<Style> styles = styleService.selectStyleBySeries(1);
		List<User> users = userService.findAll();
		List<Code> codes = codeService.selectCodesByType(1);
		modelAndView.addObject("accounts", accounts);
		modelAndView.addObject("styles", styles);
		modelAndView.addObject("users", users);
		modelAndView.addObject("codes", codes);
		return modelAndView;
	}
	
	@RequestMapping(value="/admin/account/show.json")
	public @ResponseBody ModelMap show(HttpServletRequest request){
		ModelMap modelMap = new ModelMap();
		String user = request.getParameter("user");
		String code = request.getParameter("code");
		Account account = null;
		if(StringUtils.isNotBlank(code) && StringUtils.isNotBlank(user)){
			account = accountService.selectAccountByCodeAndUser(Integer.valueOf(user), code);
		}
		modelMap.addAttribute("account", account);
		return modelMap;
	}
	
	@RequestMapping(value="/admin/account/charge.json", method=RequestMethod.POST)
	public @ResponseBody ModelMap charge(HttpServletRequest request){
		String num = request.getParameter("num");
		String user = request.getParameter("user");
		String code = request.getParameter("code");
		String username = (String) request.getSession().getAttribute("user");
		ModelMap model = new ModelMap("success", false);
		if(StringUtils.isBlank(num)){
			model.addAttribute("error", "请输入正确的数字");
			return model;
		}
		if(StringUtils.isBlank(user)){
			model.addAttribute("error", "请选择充值用户");
			return model;
		}
		if(StringUtils.isBlank(code)){
			model.addAttribute("error", "请选择充值类型");
			return model;
		}
		
		User u = userService.findOne(Integer.valueOf(user));
		if(u == null){
			model.addAttribute("error", "用户不存在");
			return model;
		}
		User admin = userService.selectUserByUserName(username);
		if(admin == null){
			model.addAttribute("error", "系统异常");
			return model;
		}
		accountService.addAccount(u.getId(), Integer.valueOf(num), code, admin.getUsername());
		model.addAttribute("success", true);
		return model;
	}

}
