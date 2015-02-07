package com.ibao.controller.video;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibao.base.util.VideoUtil;

@Controller
public class VideoController {
	
	@RequestMapping(value="/video/check.json")
	public @ResponseBody ModelMap checkVedio(HttpServletRequest request){
		String code = request.getParameter("code");
		String url = request.getParameter("url");
		String[] codes = code.split("_");
		ModelMap modelMap = new ModelMap();
		if("1".equals(codes[1]) && "1".equals(codes[2])){
			Map<String, Integer> map = VideoUtil.checkVideo(url);
			modelMap.addAttribute("map", map);
		}
		return modelMap;
	}

}
