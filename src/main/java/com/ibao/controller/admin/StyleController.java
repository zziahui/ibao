package com.ibao.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibao.model.Code;
import com.ibao.model.Style;
import com.ibao.service.base.CodeService;
import com.ibao.service.base.StyleService;

@Controller
public class StyleController {
	
	@Autowired
	private StyleService styleService;
	
	@Autowired
	private CodeService codeService;
	
	@RequestMapping(value="/admin/style.html")
	public String index(HttpServletRequest request){
		List<Style> list = styleService.findAll();
		List<Code> codes = codeService.findAll();
		request.setAttribute("list", list);
		request.setAttribute("codes", codes);
		return "admin/style/index";
	}
	
	@RequestMapping(value="/admin/style/add.json")
	public @ResponseBody ModelMap newStyle(HttpServletRequest request){
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("success", false);
		String name = request.getParameter("name");
		String group = request.getParameter("group");
		String clazz1 = request.getParameter("clazz1");
		String clazz2 = request.getParameter("clazz2");
		if(isNull(request, "name")){
			modelMap.addAttribute("error", "名称不能为空");
			return modelMap;
		}
		if(isNull(request, "group")){
			modelMap.addAttribute("error", "所属分组不能为空");
			return modelMap;
		}
		if(isNull(request, "clazz1")){
			modelMap.addAttribute("error", "类别1不能为空");
			return modelMap;
		}
		if(isNull(request, "clazz2")){
			modelMap.addAttribute("error", "类别2不能为空");
			return modelMap;
		}
		String code = group+"_"+clazz1+"_"+clazz2;
		Style style = styleService.selectStyleByCode(code);
		if(style != null){
			modelMap.addAttribute("error", "改业务类型已经存在，不能重复添加");
			return modelMap;
		}
		style = new Style();
		style.setClazz1(Integer.valueOf(clazz1));
		style.setClazz2(Integer.valueOf(clazz2));
		style.setCode(code);
		style.setGroup(Integer.valueOf(group));
		style.setName(getAName(clazz1, clazz2));
		style.setComment(name);
		styleService.create(style);
		modelMap.addAttribute("success", true);
		return modelMap;
	}
	
	@RequestMapping(value="/admin/style/del/{id}")
	public @ResponseBody ModelMap del(@PathVariable Integer id){
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("success", false);
		styleService.deleteById(id);
		modelMap.addAttribute("success", true);
		return modelMap;
	}
	
	private boolean isNull(HttpServletRequest request, String key){
		String value = request.getParameter(key);
		if(StringUtils.isBlank(value))
			return true;
		return false;
	}
	
	private String getAName(String clazz1, String clazz2){
		Code c1 = codeService.selectCodesByTypeAndCode(1, Integer.valueOf(clazz1));
		Code c2 = codeService.selectCodesByTypeAndCode(2, Integer.valueOf(clazz2));
		if(c1 != null && c2 != null){
			return c1.getName()+c2.getName();
		}
		return "";
	}

}
