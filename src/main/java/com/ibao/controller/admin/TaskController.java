package com.ibao.controller.admin;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ibao.base.util.VideoUtil;
import com.ibao.model.Account;
import com.ibao.model.Code;
import com.ibao.model.InTask;
import com.ibao.model.Style;
import com.ibao.model.Task;
import com.ibao.model.User;
import com.ibao.model.Video;
import com.ibao.service.base.AccountService;
import com.ibao.service.base.CodeService;
import com.ibao.service.base.InTaskService;
import com.ibao.service.base.StyleService;
import com.ibao.service.base.TaskService;
import com.ibao.service.base.UserService;
import com.ibao.service.base.VideoService;

@Controller
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private CodeService codeService;
	
	@Autowired
	private StyleService styleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private InTaskService inTaskService;
	
	@Autowired
	private VideoService videoService;
	
	@RequestMapping(value="/admin/task.html")
	public ModelAndView index(HttpServletRequest request){
		
		ModelAndView modelAndView = new ModelAndView();
		String username = (String) request.getSession().getAttribute("user");
		User user = userService.selectUserByUserName(username);
		List<Task> tasks = taskService.selectTaskWithStateAndUser(user.getId());
		List<Style> styles = styleService.selectStyleBySeries(1);
		List<Code> codes = codeService.selectCodesByType(1);
		modelAndView.addObject("codes", codes);
		modelAndView.addObject("styles", styles);
		modelAndView.addObject("tasks", tasks);
		modelAndView.setViewName("admin/task/index");
		return modelAndView;
	}
	
	@RequestMapping(value="/admin/task/account.json")
	public @ResponseBody ModelMap account(HttpServletRequest request){
		String username = (String) request.getSession().getAttribute("user");
		User user = userService.selectUserByUserName(username);
		Account account = null;
		String code = request.getParameter("code");
		if(user != null){
			account = accountService.selectAccountByCodeAndUser(user.getId(), code);
		}
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("account", account);
		return modelMap;
	}
	
	@RequestMapping(value="/admin/task/add.json", method=RequestMethod.POST)
	public @ResponseBody ModelMap addTask(HttpServletRequest request){
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("success", false);
		String username = (String) request.getSession().getAttribute("user");
		String code = request.getParameter("code");
		String begin = request.getParameter("begin");
		String end = request.getParameter("end");
		String num = request.getParameter("num");
		String url = request.getParameter("url");
		String server = request.getParameter("server");
		
		if(StringUtils.isBlank(code)){
			modelMap.addAttribute("error", "任务类型不能为空");
			return modelMap;
		}
		
		if(StringUtils.isBlank(url)){
			modelMap.addAttribute("error", "视频地址不能为空");
			return modelMap;
		}
		
		if(StringUtils.isBlank(num)){
			modelMap.addAttribute("error", "待刷数量不能为空");
			return modelMap;
		}
		
		if(StringUtils.isBlank(begin)){
			modelMap.addAttribute("error", "起始数量不能为空");
			return modelMap;
		}
		
		if(StringUtils.isBlank(end)){
			modelMap.addAttribute("error", "结束数量不能为空");
			return modelMap;
		}
		
		Task task = new Task();
		task.setUser(userService.selectUserByUserName(username));
		task.setUrl(url);
		task.setTotal(Integer.valueOf(num));
		task.setBegin(Integer.valueOf(begin));
		task.setEnd(Integer.valueOf(end));
		task.setStyle(styleService.selectStyleByCode(code));
		task.setState(0);
		task.setAddTime(new Date());
		task.setServer(server);
		taskService.create(task);
		modelMap.addAttribute("success", true);
		return modelMap;
	}
	
	@RequestMapping(value="/admin/task/{id}/send.json")
	public @ResponseBody ModelMap sendTask(@PathVariable Integer id, HttpServletRequest request){
		ModelMap modelMap = new ModelMap();
		Task task = taskService.findOne(id);
		modelMap.addAttribute("success", false);
		if(task == null){
			modelMap.addAttribute("error", "该任务不存在");
			return modelMap;
		}
		
		if(task.getState() != 0){
			modelMap.addAttribute("error", "该任务已经开始或者已经结束");
			return modelMap;
		}
		String username = (String) request.getSession().getAttribute("user");
		User user = userService.selectUserByUserName(username);
		Account account = accountService.selectAccountByCodeAndUser(user.getId(), task.getStyle().getCode());
		if(account == null){
			modelMap.addAttribute("error", "账户不存在，请先创建");
			return modelMap;
		}
		
		if(account.getTotal() < task.getTotal()){
			modelMap.addAttribute("error", "代刷数量不能超过账户余额");
			return modelMap;
		}
		
		if(taskService.add(task, account)){
			Map<String, Object> map = VideoUtil.getVideoInfo(task.getUrl());
			Video video = new Video();
			video.setUrl(task.getUrl());
			video.setPlaymode((Integer) map.get("playmode"));
			video.setVideoId((String) map.get("videoId"));
			video.setVideoId2((String) map.get("videoId2"));
			video.setShowId((Integer) map.get("showId"));
			video.setCatId((Integer) map.get("catId"));
			videoService.create(video);
			
			
			InTask intask = new InTask();
			intask.setCode(task.getStyle().getCode());
			intask.setTask(task.getId());
			intask.setUrl(task.getUrl());
			intask.setBegin(task.getBegin());
			intask.setEnd(task.getEnd());
			intask.setCatId(video.getCatId());
			intask.setPlaymode(video.getPlaymode());
			intask.setShowId(video.getShowId());
			intask.setVideoId(video.getVideoId());
			intask.setVideoId2(video.getVideoId2());
			intask.setServer(task.getServer());
			intask.setState(1);
			inTaskService.saveInTask(intask);
			
			
			modelMap.addAttribute("success", true);
		}else{
			modelMap.addAttribute("error", "系统错误");
			return modelMap;
		}
		return modelMap;
	}
	

}
